package in.ktechnos.test.model;

public class Data
{
    private int id;

    private String name;

    private int year;

    private String color;

    private String pantone_value;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setYear(int year){
        this.year = year;
    }
    public int getYear(){
        return this.year;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public void setPantone_value(String pantone_value){
        this.pantone_value = pantone_value;
    }
    public String getPantone_value(){
        return this.pantone_value;
    }
}

