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

        public void nowUrl(String url) {
            if (currentUrl != null) {
                backButton.push(currentUrl);
            }
            currentUrl = url;
            forwardButton.clear();
            displayUrl();
        }

        public void goBackButton() {
            if (!backButton.isEmpty()) {
                forwardButton.push(currentUrl);
                currentUrl = backButton.pop();
                displayUrl();
            } else {
                System.out.println("No pages to go back to.");
            }
        }

        public void goForwardButton() {
            if (!forwardButton.isEmpty()) {
                backButton.push(currentUrl);
                currentUrl = forwardButton.pop();
                displayUrl();
            } else {
                System.out.println("No pages to go forward to.");
            }
        }

        private void displayUrl() {
            System.out.println("Current URL: " + currentUrl);
        }

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

        public String getName() {
            return name;
        }

        public CreateTab createTab() {
            CreateTab tab = new CreateTab();
            tabs.add(tab);
            return tab;
        }

        public void addBookmark(String url) {
            bookmarks.offer(url);
        }

        public void displayBookmarks() {
            System.out.println("Bookmarks (FIFO): ");
            while (!bookmarks.isEmpty()) {
                System.out.println(bookmarks.poll());
            }
        }

        public void addShortcut(String url) {
            shortcuts.offer(url);
        }

        public void displayShortcuts() {
            System.out.println("Shortcuts (FIFO): ");
            while (!shortcuts.isEmpty()) {
                System.out.println(shortcuts.poll());
            }
        }

        public void addToHistory(String url) {
            history.push(url);
        }

        public List<String> getHistory() {
            return Collections.unmodifiableList(new ArrayList<>(history));
        }

        public void displayHistory() {
            System.out.println("History (Last Search First): ");
            Stack<String> tempHistory = (Stack<String>) history.clone();
            while (!tempHistory.isEmpty()) {
                System.out.println(tempHistory.pop());
            }
        }

        public void clearHistory() {
            history.clear();
        }

        public void clearHistoryButton() {
            clearHistory();
            System.out.println("History has been cleared.");
        }

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

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void sortProfiles() {
        // Bubble Sort implementation
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
        sortProfiles();  // Ensure profiles are sorted before displaying
        System.out.println("Profiles:");
        for (Profile profile : profiles) {
            System.out.println(profile.getName());
        }
    }

    public static void main(String[] args) {
        webbrowser webbrowserObj = new webbrowser();

        Profile profileObj1 = webbrowserObj.createProfile("Profile1");
        Profile profileObj2 = webbrowserObj.createProfile("Profile3");
        Profile profileObj3 = webbrowserObj.createProfile("Profile2");

        webbrowserObj.displayProfiles();  // Display profiles sorted

        webbrowserObj.switchProfile("Profile1");
        Profile currentProfile = webbrowserObj.getCurrentProfile();
        CreateTab tabObj1 = currentProfile.createTab();
        tabObj1.nowUrl("www.nibm.com");
        tabObj1.nowUrl("www.nibm.com/about");
        tabObj1.nowUrl("www.nibm.com/contactus");
        tabObj1.goBackButton();
        tabObj1.goBackButton();
        tabObj1.goForwardButton();
        currentProfile.addBookmark(tabObj1.getCurrentUrl());
        currentProfile.addBookmark("www.example.com");
        currentProfile.displayBookmarks();
        currentProfile.addShortcut("www.Youtube.com");
        currentProfile.addShortcut("www.Google.com");
        currentProfile.displayShortcuts();
        currentProfile.addToHistory(tabObj1.getCurrentUrl());
        currentProfile.addToHistory("www.example.com");
        currentProfile.displayHistory();
        currentProfile.switchTheme("Theme2");
        currentProfile.displayCurrentTheme();
        currentProfile.clearHistoryButton();
        System.out.println("History after clearing: " + currentProfile.getHistory());

        webbrowserObj.switchProfile("Profile2");
        System.out.println("Switched to profile: " + webbrowserObj.getCurrentProfile().getName());
        webbrowserObj.getCurrentProfile().switchTheme("Theme3");
        webbrowserObj.getCurrentProfile().displayCurrentTheme();
    }
}
