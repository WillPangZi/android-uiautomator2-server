package com.smartisan.uiautomator2.executorserver;import com.smartisan.uiautomator2.model.AndroidElement;import com.smartisan.uiautomator2.model.KnownElements;import org.json.JSONException;import org.json.JSONObject;import java.util.Hashtable;import java.util.Iterator;/** * This proxy embodies the command that the handlers execute. * 命令格式{"cmd": action/shutdown, "action": find/其他, "params":{}} */public class AndroidCommand {    JSONObject json;    public AndroidCommand(final JSONObject jsonData) {        json = jsonData;    }    /**     * Return the action string for this command.     *     * @return String     * @throws JSONException     */    public String action() throws JSONException {        if (isElementCommand()) {            return json.getString("action").substring(8);        }        return json.getString("action");    }    /**     * Get the {@link AndroidElement destEl} this command is to operate on (must     * provide the "desElId" parameter).     *     * @return {@link AndroidElement}     * @throws JSONException     */    public AndroidElement getDestElement() throws JSONException {        final String destElId = (String) params().get("destElId");        return KnownElements.getInstance().getElementFromCache(destElId);    }    /**     * Get the {@link AndroidElement element} this command is to operate on (must     * provide the "elementId" parameter).     *     * @return {@link AndroidElement}     * @throws JSONException     */    public AndroidElement getElement() throws JSONException {        final String elId = (String) params().get("elementId");        return KnownElements.getInstance().getElementFromCache(elId);    }    /**     * Returns whether or not this command is on an element (true) or device     * (false).     *     * @return boolean     */    public boolean isElementCommand() {        try {            return json.getString("action").startsWith("element:");        } catch (final JSONException e) {            return false;        }    }    /**     * 获取json格式的数据参数     *     * @return     * @throws JSONException     */    public JSONObject getJsonParams() throws JSONException {        JSONObject params = json.getJSONObject("params");        return params;    }    /**     * Return a hash table of name, value pairs as arguments to the handlers     * executing this command.     *     * @return Hashtable<String, Object>     * @throws JSONException     */    public Hashtable<String, Object> params() throws JSONException {        final JSONObject paramsObj = json.getJSONObject("params");        final Hashtable<String, Object> newParams = new Hashtable<String, Object>();        final Iterator<?> keys = paramsObj.keys();        while (keys.hasNext()) {            final String param = (String) keys.next();            newParams.put(param, paramsObj.get(param));        }        return newParams;    }}