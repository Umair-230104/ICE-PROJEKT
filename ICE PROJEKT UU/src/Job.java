public class Job {
    private String descriptionofjob;
    private String dayandtime;
    private String price;
    private String area;
    private int jobid;
    private int jobtaken;

    public Job(String descriptionofjob, String dayandtime, String price, String area, int jobid, int jobtaken) {
        this.descriptionofjob = descriptionofjob;
        this.dayandtime = dayandtime;
        this.price = price;
        this.area = area;
        this.jobid = jobid;
        this.jobtaken = jobtaken;
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

    public int getJobtaken() {
        return jobtaken;
    }

    @Override
    public String toString(){
        String s;
        if (getJobtaken() == 1 ){
            s = "Job is taken";
        }
        else{
            s = "Job is still available";
        }
        return "\n" +
                "\nJob description: " + getDescriptionofjob() +
                "\nDay and time: " + getDayandtime() +
                "\nPrice: " + getPrice() +
                "\nArea: " + getArea() +
                "\nJobID: " + getJobid() +
                " Job taken: "+s+"";
    }
}