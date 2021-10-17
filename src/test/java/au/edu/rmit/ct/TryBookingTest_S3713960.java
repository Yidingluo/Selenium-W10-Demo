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
    }


    
}