package ai.fassto.tms.externalservice.common.exception;

// TODO : 외부연동 exception 을 재정의해야할경우 사용할 수 있음.
public class ExternalApiCallException extends RuntimeException {
    public ExternalApiCallException(String customMessage) {
        super(customMessage);
    }
}
