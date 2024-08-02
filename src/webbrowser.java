import java.util.*;
public class webbrowser {
    class CreateTab{
        private Stack<String> backButton;
        private Stack<String> forwardButton;
        private String currentUrl;

        public CreateTab(){
            backButton = new Stack<>();
            forwardButton = new Stack<>();
            currentUrl = null;
        }

        //new web Url
        public void nowUrl(String url){
            if (currentUrl != null){
                backButton.push(currentUrl);
            }
            currentUrl = url;
            forwardButton.clear();
            displayUrl();
        }

        //backbutton History
        public void goBackbutton(){
            if (!backButton.isEmpty()){
                forwardButton.push(currentUrl);
                currentUrl = backButton.pop();
                displayUrl();
            }
            else {
                System.out.println("No pages to Back");
            }
        }

        //forward button History
        public void goFowardButton(){
            if (!forwardButton.isEmpty()){
                backButton.push(currentUrl);
                currentUrl = forwardButton.pop();
                displayUrl();
            }
            else {
                System.out.println("No pages to go Foward");
            }
        }

        private void displayUrl(){
            System.out.println("Current Url :" + currentUrl);
        }
        private String getCurrentUrl(){
            return currentUrl;
        }

    }

    public static void main(String[] args){
        webbrowser webbrowserObj = new webbrowser();
        CreateTab tabObj = webbrowserObj.new CreateTab();

        //Search Url
        tabObj.nowUrl("www.nibm.com");
        tabObj.nowUrl("www.nibm.com/about");
        tabObj.nowUrl("www.nibm.com/contactus");

        //back forward buttons
        tabObj.goBackbutton();
        tabObj.goBackbutton();
        tabObj.goFowardButton();


    }
}
