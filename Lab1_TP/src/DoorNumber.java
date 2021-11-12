public enum DoorNumber {
    three,
    four,
    five;

    public static DoorNumber getNumber(int number) {
        switch(number) {
            case 3:
                return three;
            case 4:
                return four;
            case 5:
                return five;
            default:
                return null;
        }
    }
}
