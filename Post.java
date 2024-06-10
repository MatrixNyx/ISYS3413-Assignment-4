package defaultPackage;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Post {
	private int postID;
	private String postTitle;
	private String postBody;
	private String [] postTags;
	private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
	private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};
	private ArrayList <Integer> postIDs = new ArrayList<>();
	private ArrayList <String> postComments = new ArrayList<>();
	//creation of variables that are used between the methods

	public boolean addPost() { //post method
		Scanner scanner = new Scanner(System.in);
		// create scanner
		System.out.println("Add title of post");
		System.out.println("Post title should have a minimum of 10 characters and a maximum of 250");
		System.out.println("First 5 characters should not be numbers or special characters");
		postTitle = scanner.nextLine();
		// ask for post title
		System.out.println("Add post body");
		System.out.println("Post body should have a minimum of 250 characters");
		postBody = scanner.nextLine();
		// ask for post body
		System.out.println("Enter number of post tags you want");
		int tagsNumber = scanner.nextInt();
		// ask for number of tags
		postTags = new String[tagsNumber];
		// Initialize array with number of tags
		
		
		System.out.println("Tags should be a minimum of 2 characters and a maximum of 10");
		System.out.println("Tags should have no capital letters");
		for(int i = 0; i < tagsNumber; i++) {
			System.out.println("Enter tag number " + (i+1));
			postTags[i] = scanner.nextLine();
		}
		//loop for number of tags
		
		for(int i = 0; i < tagsNumber; i++) {
			for(int j = 0; j < postTags[i].length(); j++) {
				if(Character.isUpperCase(postTags[i].charAt(j))) {
					return false;
				}
			}
		}
		// verify if tags are good or not
		
		System.out.println("Enter post difficulty. Enter a number from 1 to 3");
		System.out.println("Post difficulty can be Very Difficult (1), Difficult (2), or Easy (3)");
		System.out.println("Easy posts should have 3 or less tags");
		System.out.println("Difficult and Very Difficult posts should have at least 300 characters");
		int difficultyInt = scanner.nextInt() - 1;
		// ask for post difficulty
		
		
		System.out.println("Enter post urgency. Enter a number from 1 to 3");
		System.out.println("Post urgency can be: Immediately Needed (1), Highly Needed (2), or Ordinary (3)");
		int emergencyInt = scanner.nextInt() - 1;
		// ask for post emergency rating
		
		
		if(!(postTitle.length() >= 10 && postTitle.length() <= 250)) {
			return false;
		}
		// check for title validity - length
		
		
		for(int i = 0; i < 5; i++) {
			if(!(Character.isLetter(postTitle.charAt(i)))) {
				return false;
			}
		}
		// check for title validity - character type
		
		if(!(postBody.length() >= 250)) {
			return false;
		}
		//check for body length
		
		if(!(tagsNumber >= 2 && tagsNumber <= 5)) {
			return false;
		}
		//check for tags amount
		
		if(difficultyInt == 2) {
			if(tagsNumber > 3) {
				return false;
			}
			if(emergencyInt == 1 || emergencyInt == 0) {
				return false;
			}
		}else if(difficultyInt == 0 || difficultyInt == 1) {
			if(postBody.length() < 300) {
				return false;
			}
			if(emergencyInt == 2) {
				return false;
			}
		}
		//check for body length and tag amount is correct for difficulty rating assigned
		
		postID = postIDs.size();
		postIDs.add(postID);
		
		//save file
		
		String filename = "post.txt";
		String content = postTitle + "|" + postBody + "|";
		//it saves it divided by |
		
		for(int i = 0; i < postTags.length; i++) {
			content += postTags[i] + "|";
		}
		content += postTypes[difficultyInt] + "|";
		content += postEmergency[emergencyInt] + "\n";
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return true;
		//has been created
	}
	
	public boolean addComment() { //comment method
		
		Scanner scanner = new Scanner(System.in);
		int samplePostID;
		boolean matches = false;
		String comment;
		//creation of variables
		
		
		System.out.println("What post do you want to comment on? Enter post ID.");
		samplePostID = scanner.nextInt();
		//ask for post id
		
		for(int i = 0; i < postIDs.size(); i++) {
			if(samplePostID == postIDs.get(i)) {
				matches = true;
				break;
			}
		}
		//check if post exists
		
		if(matches == false) {
			return false;
		}
		
		if(postComments.get(samplePostID).length() == 5) {
			System.out.println("Too many comments on this post.");
			return false;
		}
		//check if there are too many comments already on the post
		
		
		System.out.println("What do you want to say? Keep comments a minimum of 4 words to a maximum of 10 words.");
		System.out.println("The first letter of every word should be an uppercase letter.");
		comment = scanner.nextLine();
		//ask for comment contents
		
		String[] commentWords = comment.split(" ");
		
		if(commentWords.length < 4 || commentWords.length > 10) {
			System.out.println("Comments must be between 4 and 10 words.");
			return false;
		}
		//check for the amount of words in the comment
		
		for(int i = 0; i < commentWords.length; i++) {
			if(!Character.isUpperCase(commentWords[i].charAt(0))) {
				System.out.println("Each word in comments must start with a capital letter.");
				return false;
			}
		}
		//check all the words are upper case
		
		
		//save to file
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("comment.txt"))) {
			writer.write(comment);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	public static void main(String[] args) {
		Post post = new Post();
		System.out.println(post.addPost());
	}
	//main method

}
