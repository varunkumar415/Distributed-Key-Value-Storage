package edu.iit.hawk.vkumar17.madprojectteam13;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class NowPlayingAssetLoader {
	// NowPlayingAssetLoader.readTextFile(this, "tomatoes_key.txt");
	public static String readTextFile(Context c, String name) {
		// To load text file
		InputStream input;
		try {
			AssetManager assetManager = c.getAssets();
			input = assetManager.open("helloworld.txt");

			int size = input.available();
			byte[] buffer = new byte[size];
			input.read(buffer);
			input.close();

			// byte buffer into a string
			String text = new String(buffer);
			return text;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
