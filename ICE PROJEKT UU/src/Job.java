public class Job {
    private String descriptionofjob;
    private String dayandtime;
    private String price;
    private String area;
    private int jobid;

    public Job(String descriptionofjob, String dayandtime, String price, String area, int jobid) {
        this.descriptionofjob = descriptionofjob;
        this.dayandtime = dayandtime;
        this.price = price;
        this.area = area;
        this.jobid = jobid;
    }

    public String getDescriptionofjob() {
        return descriptionofjob;
    }

    public String getDayandtime() {
        return dayandtime;
    }

    public String getPrice() {
        return price;
    }

    public String getArea() {
        return area;
    }

    public int getJobid(){
        return jobid;
    }

    @Override
    public String toString(){
        return "{Job description: "+getDescriptionofjob()+"; Day and time: "+getDayandtime()+"; Price: "+getPrice()+"; Area: "+ getArea()+"; JobID: "+getJobid()+"}";
    }
}
