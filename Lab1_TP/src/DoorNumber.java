public enum DoorNumber {

    three(3),
    four(4),
    five(5);

    private int number;

    DoorNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}
