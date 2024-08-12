import java.util.*;

public class webbrowser {
    class CreateTab {
        private Stack<String> backButton;
        private Stack<String> forwardButton;
        private String currentUrl;

        public CreateTab() {
            backButton = new Stack<>();
            forwardButton = new Stack<>();
            currentUrl = null;
        }

        //Now Url
        public void nowUrl(String url) {
            if (currentUrl != null) {
                backButton.push(currentUrl);
            }
            currentUrl = url;
            forwardButton.clear();
            displayUrl();
        }

        //Go Back Button
        public void goBackButton() {
            if (!backButton.isEmpty()) {
                forwardButton.push(currentUrl);
                currentUrl = backButton.pop();
                displayUrl();
            } else {
                System.out.println("No pages to go back to.");
            }
        }

        //Go Forward Buttom
        public void goForwardButton() {
            if (!forwardButton.isEmpty()) {
                backButton.push(currentUrl);
                currentUrl = forwardButton.pop();
                displayUrl();
            } else {
                System.out.println("No pages to go forward to.");
            }
        }

        //Prints the current URL
        private void displayUrl() {
            System.out.println("Current URL: " + currentUrl);
        }

        //Returns the current URL.
        public String getCurrentUrl() {
            return currentUrl;
        }
    }

    class Profile implements Comparable<Profile> {
        private String name;
        private List<CreateTab> tabs;
        private Queue<String> bookmarks;
        private Queue<String> shortcuts;
        private Stack<String> history;
        private String theme;

        public Profile(String name) {
            this.name = name;
            tabs = new ArrayList<>();
            bookmarks = new LinkedList<>();
            shortcuts = new LinkedList<>();
            history = new Stack<>();
            theme = "Theme1";
        }

        //Return Profile name
        public String getName() {
            return name;
        }

        //Create Tab
        public CreateTab createTab() {
            CreateTab tab = new CreateTab();
            tabs.add(tab);
            return tab;
        }
        //Add Bookarak Queue
        public void addBookmark(String url) {
            bookmarks.offer(url);
        }
        //Print Bookmarks
        public void displayBookmarks() {
            System.out.println("Bookmarks :");
            while (!bookmarks.isEmpty()) {
                System.out.println(bookmarks.poll());
            }
        }
        //Add Shortcuts Queue
        public void addShortcut(String url) {
            shortcuts.offer(url);
        }
        //Print Shortcuts
        public void displayShortcuts() {
            System.out.println("Shortcuts :");
            while (!shortcuts.isEmpty()) {
                System.out.println(shortcuts.poll());
            }
        }
        //Add Url Stack
        public void addToHistory(String url) {
            history.push(url);
        }
        //Return History
        public List<String> getHistory() {
            return Collections.unmodifiableList(new ArrayList<>(history));
        }
        //Print History
        public void displayHistory() {
            System.out.println("History :");
            Stack<String> tempHistory = (Stack<String>) history.clone();
            while (!tempHistory.isEmpty()) {
                System.out.println(tempHistory.pop());
            }
        }
        //Clear history
        public void clearHistory() {
            history.clear();
        }
        public void clearHistoryButton() {
            clearHistory();
            System.out.println("History has been cleared.");
        }
        //Set Theme
        public void setTheme(String theme) {
            if (theme == "Theme1"){
                this.theme = theme;
            } else if (theme == "Theme2") {
                this.theme = theme;
            } else if (theme == "Theme3") {
                this.theme = theme;
            }
            else {
                System.out.println("Invalid Theme!");
            }
        }
        //Return Current Theme
        public String getTheme() {
            return theme;
        }
        public void switchTheme(String newTheme) {
            setTheme(newTheme);
            System.out.println("Switched to: " + getTheme());
        }
        public void displayCurrentTheme() {
            System.out.println("Current Theme: " + getTheme());
        }
        @Override
        public int compareTo(Profile other) {
            return this.name.compareTo(other.name);
        }
    }
    private List<Profile> profiles;
    private Profile currentProfile;
    public webbrowser() {
        profiles = new ArrayList<>();
    }

    public Profile createProfile(String name) {
        Profile profile = new Profile(name);
        profiles.add(profile);
        return profile;
    }
    //Switch Profile
    public void switchProfile(String name) {
        for (Profile profile : profiles) {
            if (profile.getName().equals(name)) {
                currentProfile = profile;
                currentProfile.clearHistory();
                return;
            }
        }
        System.out.println("Profile not found.");
    }
    //Current profile
    public Profile getCurrentProfile() {
        return currentProfile;
    }
    //Buble Short
    public void sortProfiles() {
        for (int i = 0; i < profiles.size() - 1; i++) {
            for (int j = 0; j < profiles.size() - i - 1; j++) {
                if (profiles.get(j).compareTo(profiles.get(j + 1)) > 0) {
                    // Swap profiles[j] and profiles[j + 1]
                    Profile temp = profiles.get(j);
                    profiles.set(j, profiles.get(j + 1));
                    profiles.set(j + 1, temp);
                }
            }
        }
    }
    public void displayProfiles() {
        sortProfiles();
        System.out.println("Profiles :");
        for (Profile profile : profiles) {
            System.out.println(profile.getName());
        }
    }

    public static void main(String[] args) {
        webbrowser webbrowserObj = new webbrowser();
        //Create Profile
        Profile profileObj1 = webbrowserObj.createProfile("Profile1");
        Profile profileObj2 = webbrowserObj.createProfile("Profile3");
        Profile profileObj3 = webbrowserObj.createProfile("Profile2");

        //Display all Profile
        webbrowserObj.displayProfiles();

        System.out.println("____________________________________________________");

        //Switch Profile
        webbrowserObj.switchProfile("Profile1");
        //current Profile
        Profile currentProfile = webbrowserObj.getCurrentProfile();
        //Create tab
        CreateTab tabObj1 = currentProfile.createTab();
        //Url
        tabObj1.nowUrl("www.nibm.com");
        tabObj1.nowUrl("www.nibm.com/about");
        tabObj1.nowUrl("www.nibm.com/contactus");

        //go back
        tabObj1.goBackButton();
        tabObj1.goBackButton();

        //Go Forward
        tabObj1.goForwardButton();

        System.out.println("____________________________________________________");

        currentProfile.addBookmark(tabObj1.getCurrentUrl());

        System.out.println("____________________________________________________");

        //Bookmarks
        currentProfile.addBookmark("www.nibm.com");
        currentProfile.addBookmark("www.Nelflix.com");
        currentProfile.displayBookmarks();

        System.out.println("____________________________________________________");

        //Shortcuts
        currentProfile.addShortcut("www.Youtube.com");
        currentProfile.addShortcut("www.Google.com");
        currentProfile.displayShortcuts();

        System.out.println("____________________________________________________");

        //History
        currentProfile.addToHistory(tabObj1.getCurrentUrl());
        currentProfile.addToHistory("www.Facebook.com");
        currentProfile.addToHistory("www.Instargram.com");
        currentProfile.displayHistory();

        System.out.println("____________________________________________________");

        //Switch Theme
        currentProfile.switchTheme("Theme2");
        currentProfile.displayCurrentTheme();

        System.out.println("____________________________________________________");

        //Cleat Hitsory
        currentProfile.clearHistoryButton();

        System.out.println("____________________________________________________");

        //Clearded History
        System.out.println("History after clearing: " + currentProfile.getHistory());

        System.out.println("____________________________________________________");

        //Switch Profile
        webbrowserObj.switchProfile("Profile2");
        System.out.println("Switched to profile: " + webbrowserObj.getCurrentProfile().getName());

        System.out.println("____________________________________________________");

        //Theme
        webbrowserObj.getCurrentProfile().switchTheme("Theme3");
        webbrowserObj.getCurrentProfile().displayCurrentTheme();
    }
}
