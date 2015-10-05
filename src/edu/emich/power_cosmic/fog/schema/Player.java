package edu.emich.power_cosmic.fog.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Player extends FogUser {

    private String firstName;
    private String lastName;
    private BillingInformation billingInformation;
    private Set<Game> games;
    private Set<Player> friends;
    private Set<Game> wishlist;
    private List<TrophyCard> trophyCards;
    private Map<Game, PlayHistory> playHistory;

    public Player() {
    	this(null, null);
    }
    
    public Player(String username, String password) {
        super(username, password);
        games = new TreeSet<>();
        friends = new TreeSet<>();
        wishlist = new TreeSet<>();
        trophyCards = new ArrayList<>();
        playHistory = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BillingInformation getBillingInformation() {
        return billingInformation;
    }

    public void setBillingInformation(BillingInformation billingInformation) {
        this.billingInformation = billingInformation;
    }

    public Iterator<Game> getGames() {
        return games.iterator();
    }

    public boolean addGame(Game game) {
        return games.add(game);
    }
    
    public boolean giveGame(Game game, Player otherPlayer) {
    	if (games.contains(game)) {
    		this.games.remove(game);
    		otherPlayer.addGame(game);
    		return true;
    	}
    	return false;
    }

    public Iterator<Player> getFriends() {
        return friends.iterator();
    }

    public boolean addFriends(Player friend) {
        return friends.add(friend);
    }

    public boolean removeFriend(Player exFriend) {
        return friends.remove(exFriend);
    }
    
    public Iterator<Game> getWishlist() {
        return wishlist.iterator();
    }
    
    public boolean addGameToWishlist(Game game) {
        return wishlist.add(game);
    }
    
    public boolean removeGameFromWishlist(Game game) {
        return wishlist.remove(game);
    }

    public Iterator<TrophyCard> getTrophyCards() {
        return trophyCards.iterator();
    }

    public boolean addTrophyCard(TrophyCard card) {
        trophyCards.add(card);
        return true;
    }
    
    public boolean giveTrophyCard(TrophyCard card, Player friend) {
        if (trophyCards.contains(card)) {
            trophyCards.remove(card);
            friend.addTrophyCard(card);
            return true;
        } else {
            return false;
        }
    }

    public Map<Game, PlayHistory> getPlayHistory() {
        return playHistory;
    }

    public void setPlayHistory(Map<Game, PlayHistory> playHistory) {
        this.playHistory = playHistory;
    }

}
