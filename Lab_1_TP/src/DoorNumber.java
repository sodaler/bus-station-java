public enum DoorNumber {

    three, four, five;

    public static DoorNumber getNumber(int number) {
        switch(number) {
            case 0:
                return DoorNumber.three;
            case 1:
                return DoorNumber.four;
            case 2:
                return  DoorNumber.five;
        }
        return null;
    }


}
