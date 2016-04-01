package Logic;

public class Cell {

    private int value;
    private boolean fixedValue;

    public Cell(){
        value = -1;
        fixedValue = false;
    }

    public int getValue(){return value;}

    public boolean hasValue(){return value != -1;}

    public boolean hasFixedValue(){return this.fixedValue;}

    public void setFixedValue(boolean fixedValue){this.fixedValue = fixedValue;}

    public void setValue(int value){
        if(!isValid(value))
            throw new IllegalArgumentException("Value must be between 1 and 9");
        this.value = value;
    }

    public void switchValues(Cell cell){
        if(canSwitchValues(cell))
            throw new RuntimeException("You cant change the value for this cell");

        int temp = value;

        this.setValue(cell.getValue());

        cell.setValue(temp);
    }


    public boolean canSwitchValues(Cell cell){
        return cell.hasFixedValue() || this.hasFixedValue();
    }

    public boolean isValid(int value){
        return value > 0 && value < 10;
    }

}
