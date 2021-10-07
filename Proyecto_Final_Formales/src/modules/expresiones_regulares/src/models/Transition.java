package modules.expresiones_regulares.src.models;

public class Transition {

    private String originState;
    private String value;
    private String destinationState;

    public Transition(String originState, String value, String destinationState) {
        this.originState = originState;
        this.value = value;
        this.destinationState = destinationState;
    }

    public String getOriginState() {
        return originState;
    }

    public String getValue() {
        return value;
    }

    public String getDestinationState() {
        return destinationState;
    }
}
