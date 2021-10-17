/**
 Trybooking JUnit/Webdriver Tasks:
 1) Navigate to https://www.trybooking.com/book/search (Links to an external site.) and print-to-screen the featured event titles. Store these titles in a suitable variable in your test case (list? arrays?). Update your test case to use assertEquals to compare these titles with a new test case run. You are expected to show that this test case passes.
 2) Navigate to https://www.trybooking.com/book/search (Links to an external site.) as above and check for featured and events near "you". If any such event has '(Cancelled)' in its title, then this test case should fail.
    Example Event Title: Prahran Market Discovery Trail (CANCELLED)
    Example Event URL: https://www.trybooking.com/events/landing?eid=758678
 3) Navigate to https://www.trybooking.com/BUOMO (Links to an external site.) and book to one of the Monday or Thursday sessions, completing all of any optional data collection questions asked, using your student email ID.
 */
package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class TryBookingTest_S3713960 {
    WebDriver myDriver;


    @Test
    // @Disabled
    @Order(0)
    @DisplayName("Sanity test only")
     void sanityTest1(){
        // When this passes I know I have the webdriver and Junit set up correctly
        String url = "https://www.trybooking.com/book/search";
        myDriver.get(url);
        assertEquals("Buy tickets | TryBooking Australia", myDriver.getTitle());
    }

    @BeforeEach
    void setUp() {
        SeleniumDriverFactory sdf =new SeleniumDriverFactory ();
        this.myDriver = sdf.getDriver();
    }

    @AfterEach
    void tearDown() {
        //myDriver.close();
        myDriver.quit();
    }

    @Test
    @DisplayName("show all FEATURED EVENTS and add in list")
    List<WebElement> showFeaturedEvents() throws InterruptedException {
        String tryBookingURL = "https://www.trybooking.com/book/search";
        myDriver.get(tryBookingURL);
        Thread.sleep(5000);
        WebElement title1 = myDriver.findElement(By.xpath("//*[contains(text(),'Halloween')]"));
        WebElement title2 = myDriver.findElement(By.xpath("//*[contains(text(),'Museum on Stage')]"));
        WebElement title3 = myDriver.findElement(By.xpath("//*[contains(text(),'Geckos Christmas')]"));
        WebElement title4 = myDriver.findElement(By.xpath("//*[contains(text(),'2021 Nambung')]"));
        WebElement title5 = myDriver.findElement(By.xpath("//*[contains(text(),'Welding for Art')]"));
        WebElement title6 = myDriver.findElement(By.xpath("//*[contains(text(),'Joondalup Resort')]"));
        List <WebElement> allFeatureEvents = new ArrayList<>();
        allFeatureEvents.add(title1);
        allFeatureEvents.add(title2);
        allFeatureEvents.add(title3);
        allFeatureEvents.add(title4);
        allFeatureEvents.add(title5);
        allFeatureEvents.add(title6);
        System.out.println(allFeatureEvents.get(0).getText());
        System.out.println(allFeatureEvents.get(1).getText());
        System.out.println(allFeatureEvents.get(2).getText());
        System.out.println(allFeatureEvents.get(3).getText());
        System.out.println(allFeatureEvents.get(4).getText());
        System.out.println(allFeatureEvents.get(5).getText());

        return allFeatureEvents;
    }

    @Test
    @DisplayName("check all FEATURED EVENTS title correct ")
    void checkTitle() throws InterruptedException
    {
        List <WebElement> allFeatureEvents = showFeaturedEvents();
        
        assertEquals("Halloween at the Hall", allFeatureEvents.get(0).getText());
        assertEquals("Museum on Stage", allFeatureEvents.get(1).getText());
        assertEquals("Geckos Christmas Party 2021", allFeatureEvents.get(2).getText());
        assertEquals("2021 Nambung Country Music Muster", allFeatureEvents.get(3).getText());
        assertEquals("30th October 2021 Welding for Art", allFeatureEvents.get(4).getText());
        assertEquals("Joondalup Resort Murder Mystery Night", allFeatureEvents.get(5).getText());
        allFeatureEvents.clear();
    }


    @Test
    @DisplayName("show all events near you and when any such event has '(Cancelled)' in its title, then this test case should fail")
    void checkNearYouTitle(){
        String tryBookingURL = "https://www.trybooking.com/book/search";
        myDriver.get(tryBookingURL);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement eventsNearbySection = myDriver.findElement(By.id("EventsNearbySection"));
        List <WebElement> initSearchResult = eventsNearbySection.findElements(By.className("init-search-result"));
        for (WebElement nearBy : initSearchResult){ 
           System.out.println(nearBy.findElement(By.tagName("h2")).getText());
           String cancelled = "CANCELLED";
           //-1 = false
           assertEquals(-1, nearBy.findElement(By.tagName("h2")).getText().indexOf(cancelled));
        }
    }


    @Test
    @DisplayName("book to one of the Monday sessions, completing data collection questions asked, using student email.")
    void bookMonday() throws InterruptedException{
        String bookingMondayURL = " https://www.trybooking.com/BUOMO";
        myDriver.get(bookingMondayURL);
            Thread.sleep(3000);
        WebElement table = myDriver.findElement(By.id("sessions-table"));
        System.out.println(table.getText());
        List<WebElement> slt = table.findElements(By.xpath("//button[contains(text(),' Select')]"));
        slt.get(1).click();

        Thread.sleep(7000);
        WebElement sectionTickets = myDriver.findElement(By.id("bookingTicketsForm"));
        sectionTickets.findElement(By.name("quantity0")).sendKeys("1");
        Thread.sleep(5000);
        myDriver.findElement(By.id("Next_addToCartBtn")).click();
        Thread.sleep(5000);
        List <WebElement> birthday = myDriver.findElements(By.tagName("select"));
        System.out.println(birthday.size());
        WebElement months = birthday.get(0);
        months.findElement(By.xpath("option[12]")).click();
        // Thread.sleep(1000);
        WebElement year = birthday.get(2); 
        year.findElement(By.xpath("option[36]")).click();
        // Thread.sleep(1000);
        WebElement days = birthday.get(1);
        days.findElement(By.xpath("option[20]")).click();
        // Thread.sleep(1000);
        WebElement workingVideo = birthday.get(3);
        workingVideo.findElement(By.xpath("option[1]")).click();
        // Thread.sleep(1000);
        myDriver.findElement(By.id("ticketHolderDetails_Next")).click();
        Thread.sleep(5000);
        myDriver.findElement(By.id("txtFirstName")).sendKeys("Yiding");
        // Thread.sleep(1000);
        myDriver.findElement(By.id("txtLastName")).sendKeys("Luo");
        // Thread.sleep(1000);
        myDriver.findElement(By.xpath("//select[@id='drpCountry']/option[51]")).click();
        // Thread.sleep(1000);
        myDriver.findElement(By.id("txtEmailAddress")).sendKeys("s3713960@student.rmit.edu.au");
        // Thread.sleep(1000);
        myDriver.findElement(By.id("txtConfirmEmailAddress")).sendKeys("s3713960@student.rmit.edu.au");
        // Thread.sleep(1000);
        myDriver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
    }
}