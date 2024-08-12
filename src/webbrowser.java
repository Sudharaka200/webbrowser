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

        // New web URL
        public void nowUrl(String url) {
            if (currentUrl != null) {
                backButton.push(currentUrl);
            }
            currentUrl = url;
            forwardButton.clear();
            displayUrl();
        }

        // Back button history
        public void goBackButton() {
            if (!backButton.isEmpty()) {
                forwardButton.push(currentUrl);
                currentUrl = backButton.pop();
                displayUrl();
            } else {
                System.out.println("No pages to go back to.");
            }
        }

        // Forward button history
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

    // Profile class
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
            bookmarks = new LinkedList<>();  // Using LinkedList as a Queue
            shortcuts = new LinkedList<>();  // Using LinkedList as a Queue
            history = new Stack<>();         // Using Stack for History
            theme = "Theme1";                // Default theme
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
            bookmarks.offer(url);  // Enqueue the bookmark
        }

        public void displayBookmarks() {
            System.out.println("Bookmarks (FIFO): ");
            while (!bookmarks.isEmpty()) {
                System.out.println(bookmarks.poll());  // Dequeue and display
            }
        }

        public void addShortcut(String url) {
            shortcuts.offer(url);  // Enqueue the shortcut
        }

        public void displayShortcuts() {
            System.out.println("Shortcuts (FIFO): ");
            while (!shortcuts.isEmpty()) {
                System.out.println(shortcuts.poll());  // Dequeue and display
            }
        }

        public void addToHistory(String url) {
            history.push(url);  // Push the URL onto the stack
        }

        public List<String> getHistory() {
            return Collections.unmodifiableList(new ArrayList<>(history));
        }

        public void displayHistory() {
            System.out.println("History (Last Search First): ");
            Stack<String> tempHistory = (Stack<String>) history.clone(); // Clone the stack to preserve the original
            while (!tempHistory.isEmpty()) {
                System.out.println(tempHistory.pop());  // Pop and display
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
            if (theme.equals("Theme1") || theme.equals("Theme2") || theme.equals("Theme3")) {
                this.theme = theme;
            } else {
                System.out.println("Invalid theme! Available themes: Theme1, Theme2, Theme3.");
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
        sortProfiles();  // Sort profiles after adding a new one
        return profile;
    }

    private void sortProfiles() {
        Collections.sort(profiles);
    }

    public void switchProfile(String name) {
        for (Profile profile : profiles) {
            if (profile.getName().equals(name)) {
                currentProfile = profile;
                currentProfile.clearHistory();  // Clear history when switching profiles
                return;
            }
        }
        System.out.println("Profile not found.");
    }

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    // Method to display all created profiles
    public void displayAllProfiles() {
        System.out.println("All Profiles:");
        for (Profile profile : profiles) {
            System.out.println(profile.getName());
        }
    }

    public static void main(String[] args) {
        webbrowser webbrowserObj = new webbrowser();

        // Create Profiles
        Profile profileObj1 = webbrowserObj.createProfile("Profile1");
        Profile profileObj2 = webbrowserObj.createProfile("Profile2");
        Profile profileObj3 = webbrowserObj.createProfile("Profile3");

        // Display all created profiles
        webbrowserObj.displayAllProfiles();

        // Switch to Profile1
        webbrowserObj.switchProfile("Profile1");
        Profile currentProfile = webbrowserObj.getCurrentProfile();

        // Create a tab and navigate URLs
        CreateTab tabObj1 = currentProfile.createTab();
        tabObj1.nowUrl("www.nibm.com");
        tabObj1.nowUrl("www.nibm.com/about");
        tabObj1.nowUrl("www.nibm.com/contactus");

        // Back and forward navigation
        tabObj1.goBackButton();
        tabObj1.goBackButton();
        tabObj1.goForwardButton();

        // Add a bookmark
        currentProfile.addBookmark(tabObj1.getCurrentUrl());
        currentProfile.addBookmark("www.example.com");

        // Display bookmarks
        currentProfile.displayBookmarks();

        // Add a shortcut
        currentProfile.addShortcut("www.Youtube.com");
        currentProfile.addShortcut("www.Google.com");

        // Display shortcuts
        currentProfile.displayShortcuts();

        // Add to history
        currentProfile.addToHistory(tabObj1.getCurrentUrl());
        currentProfile.addToHistory("www.example.com");

        // Display history
        currentProfile.displayHistory();

        // Switch and display theme
        currentProfile.switchTheme("Theme2");

        // Display the current theme
        currentProfile.displayCurrentTheme();

        // Clear history using the "Clear History" button
        currentProfile.clearHistoryButton();
        System.out.println("History after clearing: " + currentProfile.getHistory());

        // Switch to another profile
        webbrowserObj.switchProfile("Profile2");
        System.out.println("Switched to profile: " + webbrowserObj.getCurrentProfile().getName());

        // Switch to a new theme in Profile2
        webbrowserObj.getCurrentProfile().switchTheme("Theme3");

        // Display the current theme in Profile2
        webbrowserObj.getCurrentProfile().displayCurrentTheme();

    }
}
