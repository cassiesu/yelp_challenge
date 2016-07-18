/**
 * List of restaurant, basic class which stores the attributes information of each restaurants
 * @author Think
 *
 */
import java.util.*;

public class ListOfRestaurants {
     
    private int id;
     
 
    private double stars;
    private int review_count;
    private int price;
     
    private int wifi;
    private int caters;
    private int delivery;
    private int goodForKids;
    private int reservations;
    private int wheelchair;
    private int dancing;
    private int coatCheck;
    private int smoking;
    private int creditCards;
     
    private int takeout;
    private int happyhour;
    private int outdoorSeating;
    private int waiterService;
     
    private int dessert;
    private int latenight;
    private int lunch;
    private int dinner;
    private int brunch;
    private int breakfast;
    private int tv;
    private int goodForGroups;
     
    private int romantic;
    private int intimate;
    private int touristy;
    private int hipster;
    private int divey;
    private int classy;
    private int trendy;
    private int upscale;
    private int casual;
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public int noiseLevel;
    /**
     * features in Categories  
     */
    public Set<String> Categories = new HashSet<String>();
    public static Set<String> totalCate = new HashSet<String>();
    /**
     * features for parking
     */
    public Map<String, Integer> Parking = new HashMap<String, Integer>();
    /**
     * features for hours
     */
    //!!!!!!!!!!!!!!!!!!!!!!
    
    public ListOfRestaurants(){
    	this.noiseLevel = 0;
        this.id = 0;
        this.stars = 0.0;
        this.review_count = 0;
        this.price = 0;     
        this.wifi = 0;
         
        this.caters = 0;
        this.delivery = 0;
        this.goodForKids = 0;
        this.reservations = 0;
        this.wheelchair = 0;
        this.dancing = 0;
        this.coatCheck = 0;
        this.smoking = 0;
        this.creditCards = 0;
         
        this.takeout = 0;
        this.happyhour = 0;
        this.outdoorSeating = 0;
        this.waiterService = 0;
         
        this.dessert = 0;
        this.latenight = 0;
        this.lunch = 0;
        this.dinner = 0;
        this.brunch = 0;
        this.breakfast = 0;
        this.tv = 0;
        this.goodForGroups = 0;
         
        this.setRomantic(0);
        this.setIntimate(0);
        this.setTouristy(0);
        this.setHipster(0);
        this.setDivey(0);
        this.setClassy(0);
        this.setTrendy(0);
        this.setUpscale(0);
        this.setCasual(0);           
    }
     
    public double getStars() {
        return stars;
    }
    public void setStars(double stars) {
        this.stars = stars;
    }
    public double getReview_count() {
        return review_count;
    }
    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
     
    public void print()
     
    {
        System.out.println("=====================================");
        System.out.println("Restautant " + this.id );       
        System.out.println("price: " + this.price+ "\n" + "review counter: " + this.review_count);
        System.out.println("=====================================");
 
         
         
         
    }
 
    public int getCaters() {
        return caters;
    }
 
    public void setCaters(int caters) {
        this.caters = caters;
    }
 
    public int getDelivery() {
        return delivery;
    }
 
    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }
 
    public int getGoodForKids() {
        return goodForKids;
    }
 
    public void setGoodForKids(int goodForKids) {
        this.goodForKids = goodForKids;
    }
 
    public int getReservations() {
        return reservations;
    }
 
    public void setReservations(int reservations) {
        this.reservations = reservations;
    }
 
    public int getWheelchair() {
        return wheelchair;
    }
 
    public void setWheelchair(int wheelchair) {
        this.wheelchair = wheelchair;
    }
 
    public int getDancing() {
        return dancing;
    }
 
    public void setDancing(int dancing) {
        this.dancing = dancing;
    }
 
    public int getCoatCheck() {
        return coatCheck;
    }
 
    public void setCoatCheck(int coatCheck) {
        this.coatCheck = coatCheck;
    }
 
    public int getSmoking() {
        return smoking;
    }
 
    public void setSmoking(int smoking) {
        this.smoking = smoking;
    }
 
    public int getCreditCards() {
        return creditCards;
    }
 
    public void setCreditCards(int creditCards) {
        this.creditCards = creditCards;
    }
 
    public int getTakeout() {
        return takeout;
    }
 
    public void setTakeout(int takeout) {
        this.takeout = takeout;
    }
 
    public int getHappyhour() {
        return happyhour;
    }
 
    public void setHappyhour(int happyhour) {
        this.happyhour = happyhour;
    }
 
    public int getOutdoorSeating() {
        return outdoorSeating;
    }
 
    public void setOutdoorSeating(int outdoorSeating) {
        this.outdoorSeating = outdoorSeating;
    }
 
    public int getWaiterService() {
        return waiterService;
    }
 
    public void setWaiterService(int waiterService) {
        this.waiterService = waiterService;
    }
 
    public int getDessert() {
        return dessert;
    }
 
    public void setDessert(int dessert) {
        this.dessert = dessert;
    }
 
    public int getLatenight() {
        return latenight;
    }
 
    public void setLatenight(int latenight) {
        this.latenight = latenight;
    }
 
    public int getLunch() {
        return lunch;
    }
 
    public void setLunch(int lunch) {
        this.lunch = lunch;
    }
 
    public int getDinner() {
        return dinner;
    }
 
    public void setDinner(int dinner) {
        this.dinner = dinner;
    }
 
    public int getBrunch() {
        return brunch;
    }
 
    public void setBrunch(int brunch) {
        this.brunch = brunch;
    }
 
    public int getBreakfast() {
        return breakfast;
    }
 
    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }
 
    public int getTv() {
        return tv;
    }
 
    public void setTv(int tv) {
        this.tv = tv;
    }
 
    public int getGoodForGroups() {
        return goodForGroups;
    }
 
    public void setGoodForGroups(int goodForGroups) {
        this.goodForGroups = goodForGroups;
    }
     
    public int getWifi() {
        return wifi;
    }
 
    public void setWifi(int wifi) {
        this.wifi = wifi;
    }
 
    public int getRomantic() {
        return romantic;
    }
 
    public void setRomantic(int romantic) {
        this.romantic = romantic;
    }
 
    public int getIntimate() {
        return intimate;
    }
 
    public void setIntimate(int intimate) {
        this.intimate = intimate;
    }
 
    public int getTouristy() {
        return touristy;
    }
 
    public void setTouristy(int touristy) {
        this.touristy = touristy;
    }
 
    public int getHipster() {
        return hipster;
    }
 
    public void setHipster(int hipster) {
        this.hipster = hipster;
    }
 
    public int getDivey() {
        return divey;
    }
 
    public void setDivey(int divey) {
        this.divey = divey;
    }
 
    public int getClassy() {
        return classy;
    }
 
    public void setClassy(int classy) {
        this.classy = classy;
    }
 
    public int getTrendy() {
        return trendy;
    }
 
    public void setTrendy(int trendy) {
        this.trendy = trendy;
    }
 
    public int getUpscale() {
        return upscale;
    }
 
    public void setUpscale(int upscale) {
        this.upscale = upscale;
    }
 
    public int getCasual() {
        return casual;
    }
 
    public void setCasual(int casual) {
        this.casual = casual;
    }
}