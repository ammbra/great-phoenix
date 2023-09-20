package org.example.jep444;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicPlayerTraditional {
	public static void main(String[] args) {
		List<String> playlist = List.of("song1.mp3", "song2.mp3", "song3.mp3", "song4.mp3","song5.mp3", "song6.mp3" );
		List<User> users = new ArrayList<>();
		users.add(new User("User1", playlist));
		users.add(new User("User2", playlist.subList(2,6)));
		users.add(new User("User3", playlist.subList(0, 4)));

		List<Thread> threads = new ArrayList<>();
		users.forEach(user -> {
			Thread platformThread = new Thread(() -> {
				System.out.println(user.name() + " is using the app on " + Thread.currentThread());
				user.playlist().forEach(song -> {
					System.out.println(Thread.currentThread().getName() + " playing: " + song + " on " + Thread.currentThread());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					System.out.println(Thread.currentThread().getName() + user.name()+ " finished playing: " + song);
				});
			}, user.name() + "-Thread");
			platformThread.start();
			threads.add(platformThread);
		});

		// Wait for all platform threads to finish
		threads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});
	}

}
