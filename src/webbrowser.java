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
    //Profile
class Profile{
        private String name;
        private List<CreateTab> tabs;
        private List<String> bookmarks;
        private List<String> shortcuts;
        private List<String> history;
        private String theme;

        public Profile(String name){
            this.name = name;
            tabs = new ArrayList<>();
            bookmarks = new ArrayList<>();
            shortcuts = new ArrayList<>();
            history = new ArrayList<>();
            theme = "theme-1";
        }
        public String getName(){
            return name;
        }
        public CreateTab createTab() {
            CreateTab tab = new CreateTab();
            tabs.add(tab);
            return tab;
        }
        public void addBookmark(String url) {
            bookmarks.add(url);
        }

        public List<String> getBookmarks() {
            return bookmarks;
        }
        public void addShortcut(String url) {
            shortcuts.add(url);
        }

        public List<String> getShortcuts() {
            return shortcuts;
        }

        public void addToHistory(String url) {
            history.add(url);
        }
        public List<String> getHistory() {
            return history;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getTheme() {
            return theme;
        }
    }
           private Map<String, Profile> profiles;
    private Profile currentProfile;

    public webbrowser() {
        profiles = new HashMap<>();
    }

    public Profile createProfile(String name) {
        Profile profile = new Profile(name);
        profiles.put(name, profile);
        return profile;
    }

    public void switchProfile(String name) {
        currentProfile = profiles.get(name);
    }

    public Profile getCurrentProfile() {
        return currentProfile;
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

        //Bookmarks
        currentProfile.addBookmark("www.nibm.com");
        System.out.println("Added Bookmark :" + currentProfile.getBookmarks());
        //Shortcuts
        currentProfile.addShortcut("www.Youtube.com");
        System.out.println("Added Shortcuts :" + currentProfile.getShortcuts());
        //History
        currentProfile.addToHistory(tabObj1.getCurrentUrl());
        System.out.println("History" + currentProfile.getHistory());
        //Theme
        currentProfile.setTheme("Theme1");
        System.out.println("Selected Theme :" + currentProfile.getTheme());


    }
}
