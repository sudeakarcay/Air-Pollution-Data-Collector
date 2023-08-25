package co.mobileaction.example.web.client;


public interface IHttpRequestExecutor
{
    <T> T executeGetRequest(String url, Class<T> resultClass);

}
