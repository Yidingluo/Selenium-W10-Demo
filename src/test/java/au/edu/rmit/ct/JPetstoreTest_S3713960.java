/**
 JPetstore JUnit/Webdriver Tasks:
 1) Check the pet name, price and check if there is stock for one pet of your choice (outside of Male Chihuahua). (as outlined for W11 prac)
 2) Start a menagerie! Select a specific fish, specific cat, and a third pet (they will have a unique item ID). Add 3 multiples of the first, 2 multiples of the cat, and one of the third pet to the cart. Check the subtotal matches the expected price. You are expected to show that this test case passes.  */
package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class JPetstoreTest_S3713960 {
    WebDriver myDriver;


    @Test
    // @Disabled
    @Order(0)
    @DisplayName("Click on hyperlink to Enter Petstore website")
     void testWebsite(){
        // When this passes I know I have the webdriver and Junit set up correctly
        String petStoreURL = "https://petstore.octoperf.com";
        myDriver.get(petStoreURL);
        assertEquals("JPetStore Demo", myDriver.getTitle());
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
    @DisplayName("Check pet name price and is stock for Male Puppy Poodle at product page")
    void checkPoodle(){
        String poodleURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-8";
        myDriver.get(poodleURL);
        List<WebElement> poodleTD = myDriver.findElements(By.tagName("td"));
        System.out.println("Printing text from <td> elements:");
        for (WebElement poodle : poodleTD){ 
            System.out.print(poodle.getText() + ", ");
        }
        WebElement poodleName = poodleTD.get(2);
        assertEquals("Male Puppy Poodle", poodleName.getText());
        WebElement poodlePrice = poodleTD.get(5);
        assertEquals("$18.50", poodlePrice.getText());
        WebElement poodleInStock = poodleTD.get(4);
        String inStock = poodleInStock.getText();
        String regEx="[^0-9]";  
        Pattern p = Pattern.compile(regEx);  
        Matcher m = p.matcher(inStock);  
        int numberInStock = Integer.parseInt(m.replaceAll("").trim());
        System.out.println(numberInStock);
        assertEquals(true, numberInStock > 0);
    }

    @Test
    @DisplayName("add 3 Spotted Koi, 2 Adult Male Persian and 1 Adult Male Finch in cart and check price")
    void menageriePrice(){
        String spottedKoiURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-4";
        myDriver.get(spottedKoiURL);
        WebElement ac = myDriver.findElement(By.className("Button"));
        if (Objects.equals(ac.getText(), "Add to Cart")){
            ac.click();
        }
        String spottedKoi2URL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-4";
        myDriver.get(spottedKoi2URL);
        WebElement ac2 = myDriver.findElement(By.className("Button"));
        if (Objects.equals(ac2.getText(), "Add to Cart")){
            ac2.click();
        }
        String spottedKoi3URL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-4";
        myDriver.get(spottedKoi3URL);
        WebElement ac3 = myDriver.findElement(By.className("Button"));
        if (Objects.equals(ac3.getText(), "Add to Cart")){
            ac3.click();
        }
        String adultMalePersianURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-17";
        myDriver.get(adultMalePersianURL);
        WebElement ac4 = myDriver.findElement(By.className("Button"));
        if (Objects.equals(ac4.getText(), "Add to Cart")){
            ac4.click();
        }
        String adultMalePersian2URL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-17";
        myDriver.get(adultMalePersian2URL);
        WebElement ac5 = myDriver.findElement(By.className("Button"));
        if (Objects.equals(ac5.getText(), "Add to Cart")){
            ac5.click();
        }
        String adultMaleFinchURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-19";
        myDriver.get(adultMaleFinchURL);
        WebElement ac6 = myDriver.findElement(By.className("Button"));
        if (Objects.equals(ac6.getText(), "Add to Cart")){
            ac6.click();
        }
        WebElement subTotal = myDriver.findElement(By.xpath("//*[contains(text(),'Sub Total')]"));
        System.out.println("Mary's order successful: " + subTotal.getText());
        assertEquals("Sub Total: $258.00", subTotal.getText());
    }
}