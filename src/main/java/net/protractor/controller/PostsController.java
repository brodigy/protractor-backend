package net.protractor.controller;

import net.protractor.model.Post;
import net.protractor.model.User;
import net.protractor.model.UsersConfig;
import net.protractor.utils.RandomString;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

@Controller
public class PostsController {

	public static ArrayList<Post> posts = new ArrayList<Post>();
	public static ArrayList<User> users = new ArrayList<User>();


	@RequestMapping(value = "/posts", method = {RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ArrayList<Post> search() {
		if (PostsController.posts.size() == 0) {
			PostsController.posts.addAll(samplePosts());
		}

		return PostsController.posts;
	}

	@RequestMapping(value = "/clear", method = {RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Integer clear() {
		PostsController.posts.clear();

		return PostsController.posts.size();
	}

	@RequestMapping(value = "/clearUsers", method = {RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Integer clearUsers() {
		PostsController.users.clear();

		return PostsController.users.size();
	}

	@RequestMapping(value = "/**", method = {RequestMethod.OPTIONS})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void options() {
	}

	@RequestMapping(value = "/publishPost", method = {RequestMethod.POST})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Post save(HttpServletRequest request) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		Post post = null;
		if (request.getInputStream() != null) {
			InputStreamReader reader = new InputStreamReader(request.getInputStream());
			post = objectMapper.readValue(reader, Post.class);
		}

		PostsController.posts.add(post);

		return post;
	}

	@RequestMapping(value = "/saveUser", method = {RequestMethod.POST})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User saveUser(HttpServletRequest request) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		User user = null;
		if (request.getInputStream() != null) {
			InputStreamReader reader = new InputStreamReader(request.getInputStream());
			user = objectMapper.readValue(reader, User.class);
		}

		PostsController.users.add(user);

		return user;
	}

	@RequestMapping(value = "/generateUsers", method = {RequestMethod.POST})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public int generateUsers(HttpServletRequest request) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		UsersConfig usersConfig = null;
		if (request.getInputStream() != null) {
			InputStreamReader reader = new InputStreamReader(request.getInputStream());
			usersConfig = objectMapper.readValue(reader, UsersConfig.class);
		}

		generateUsers(usersConfig.getNumber());


		return PostsController.users.size();
	}

	@RequestMapping(value = "/users", method = {RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ArrayList<User> getUsers() {

		return PostsController.users;
	}

	private void generateUsers(int nr) {
		for(int i = 0; i < nr; i++){
			RandomString randomString = new RandomString(10);
			String[] genders = {"Male", "Female", "Unspecified"};
			Random random = new Random();

			PostsController.users.add(
					new User(randomString.nextString(), randomString.nextString(), randomString.nextString(), genders[random.nextInt(2)], randomString.nextString()));
		}

	}

	private ArrayList<Post> samplePosts() {

		ArrayList<Post> posts = new ArrayList<Post>();

		posts.add(new Post("MIT Study suggests current solar power tech is good enough",
				"The standard line about solar power is that while good in theory, the technology just isn't there to keep our lights on and our Netflix streaming. But a new study from MIT (PDF) suggests that's not the case. According to the massive report (an epic 356 pages) current crystalline silicon photovoltaic technology is capable of delivering terawatt-scale power by 2050. That would be many times larger than Topaz facility California that generates 550 megawatts. While there is certainly room for improvement in efficiency, the MIT study says that the biggest hurdle isn't tech, it's investment. The authors called out the lack of funding for research and development, but focused more on poor governmental policies. Subsidies generally go to other energy sources, like oil and natural gas, and trade policies set by the federal government have driven up prices by restricting imports of cheaper solar parts in order to boost domestic production.", "MIT"));

		posts.add(new Post("NASA thinks a robotic eel might be the key to exploring Europa", "We've seen the submarine that NASA wants to explore Saturn's moon Titan with, but compared to what the aeronautics outfit's looking at for icy climes like Jupiter's Europa it's downright pedestrian. The wormy-looking contraption up above is actually considered a type of amphibious rover and it's pretty different from the Deep-SCINI we've seen previously. Because there aren't exactly electrical outlets anywhere aside from Earth and relying on solar power might not always be feasible, it has to use alternative means for energy. In this case, NASA says antenna on the soft robot's back would draw energy from \"locally changing magnetic fields.\"", "NASA"));

		return posts;
	}
}
