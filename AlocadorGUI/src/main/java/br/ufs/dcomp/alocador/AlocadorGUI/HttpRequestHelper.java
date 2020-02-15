package br.ufs.dcomp.alocador.AlocadorGUI;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Classe facilitadora ao acesso a requisição HTTP através do contexto JSF.
 *
 * @author Darlan Oliveira
 */
public abstract class HttpRequestHelper {

	/**
     * Logger desta classe
     */
    /**
     * Método utilitário para obtenção da instância corrente do contexto JSF.
     *
     * @return FacesContext O contexto JSF corrente.
     * @roseuid 42A448D9017B
     */
    private static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    /**
     * Método utilitário para obtenção da instância corrente do contexto externo
     * ao JSF.
     *
     * @return ExternalContext O contexto externo ao JSF corrente.
     * @roseuid 42A448D90189
     */
    private static ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }
    /**
     * Método responsável por retornar os parâmetros (String) da requisição.
     *
     * @return Map com os parâmetros (String) da requisição.
     * @roseuid 42A448D90199
     */
    public static Map<String, String> getParameterMap() {
        return getExternalContext().getRequestParameterMap();
    }
    /**
     * Método responsável por retornar os objetos (Object) da requisição.
     *
     * @return Map com os objetos (Object) da requisição.
     * @roseuid 42A448D901A9
     */
    public static Map<String, Object> getAttributeMap() {
        return getExternalContext().getRequestMap();
    }
    /**
     * Método responsável por retornar os objetos (Object) da sessão.
     *
     * @return Map com os objetos (Object) da sessão.
     * @roseuid 42A448D901B9
     */
    public static Map<String, Object> getSessionMap() {
        return getExternalContext().getSessionMap();
    }
    /**
     * Pesquisa o valor do parâmetro (String) indicado e retorna seu valor caso
     * seja diferente de null, "" e "null". Caso seja um dos valores informados
     * anteriormente, retorna nulo (null). Equivale a:
     * getParameter(getParameterMap(), name).
     *
     * @param name Nome do parâmetro
     * @param name
     * @return String Valor do parâmetro
     * @roseuid 42A44AAF03A5
     */
    public static String getParameter(final String name) {
        return getParameter(getParameterMap(), name);
    }
    /**
     * Pesquisa o valor do objeto (Object) indicado e retorna seu valor ou nulo
     * caso não encontre. Equivale a: getRequestParameter(getRequestMap(),
     * name).
     *
     * @param key Nome do objeto
     * @param name
     * @return Object Valor do objeto
     * @roseuid 42A44AB0003A
     */
    public static Object getAttribute(final Object key) {
        return getAttribute(getAttributeMap(), key);
    }
    /**
     * Pesquisa o valor do objeto (Object) indicado na sessão e retorna seu
     * valor ou nulo caso não encontre.
     *
     * @param name Nome do objeto
     * @param name
     * @return Object Valor do objeto
     * @roseuid 42A44AB000D7
     */
    public static Object getSessionValue(final Object name) {
        return getSessionValue(getSessionMap(), name);
    }
    /**
     * Pesquisa o valor do parâmetro (String) indicado e retorna seu valor caso
     * seja diferente de null, "" e "null". Caso seja um dos valores informados
     * anteriormente, retorna nulo (null).
     *
     * @param paramMap Parâmetros da requisição.
     * @param name Nome do parâmetro.
     * @param paramMap
     * @param name
     * @return String Valor do parâmetro.
     * @roseuid 42A44AB00155
     */
    public static String getParameter(final Map<String, String> paramMap,
            final String name) {
              String result;
        if (paramMap == null) {
            result = null;
        } else {
            String param = (String) paramMap.get(name);
            if (param != null && !(param = param.trim()).equals("")
                    && !param.equals("null")) { //$NON-NLS-1$
                result = param;
            } else {
                result = null;
            }
        }
     
        return result;
    }
    /**
     * Pesquisa o valor do objeto (Object) indicado e retorna seu valor ou nulo
     * caso não encontre.
     *
     * @param requestMap Map da requisição.
     * @param key Chave do objeto
     * @param requestMap
     * @param name
     * @return Object Valor do objeto
     * @roseuid 42A44AB00201
     */
    public static Object getAttribute(final Map<String, Object> requestMap,
            final Object key) {
       
        Object result;
        if (requestMap == null) {
            result = null;
        } else {
            result = requestMap.get(key);
        }
       
        return result;
    }
    /**
     * Pesquisa o valor do objeto (Object) indicado na sessão e retorna seu
     * valor ou nulo caso não encontre.
     *
     * @param sessionMap Map da sessão.
     * @param key Chave do objeto
     * @param sessionMap
     * @param name
     * @return Object Valor do objeto
     * @roseuid 42A44AB0029E
     */
    public static Object getSessionValue(final Map<String, Object> sessionMap,
            final Object key) {
   
        Object result;
        if (sessionMap == null) {
            result = null;
        } else {
            result = sessionMap.get(key);
        }
              return result;
    }
    /**
     * Retorna as informações referentes aos certificados pessoais informados pelo
     * cliente.
     *
     * @return vetor contendo as informações dos certificados pessoais.
     */
    public static X509Certificate[] getX509Certificates() {
        X509Certificate[] result = (java.security.cert.X509Certificate[]) getAttribute("javax.servlet.request.X509Certificate");
        return result;
    }
}