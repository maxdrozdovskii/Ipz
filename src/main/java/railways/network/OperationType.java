package railways.network;

public enum OperationType {
    LOGIN,
    DELETE,
    REGISTER_USER,
    GET_JOURNEYS,
    GET_JOURNEYS_COUNT,
    GET_JOURNEYS_FOR_PERIOD,
    RECEIVE_JOURNEY,
    GET_RECEIVED_PLACES,
    GET_USER_JOURNEYS,
    DELETE_USER_JOURNEY,
    DELETE_JOURNEY,
    ADD_JOURNEY;

    @Override
    public String toString() {
        return this.name();
    }
}
