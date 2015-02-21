package com.avengers.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.avengers.model.*;
import com.avengers.constants.FilePath;

public class DependentParser {

	private List<String[]> dependentPath;

	public List<String[]> getDependentPath() {
		return dependentPath;
	}

	public DependentParser(String filePath) throws IOException {
		dependentPath = new ArrayList<String[]>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		String[] temp;
		line = br.readLine();

		while (line != null) {
			line = line.trim();
			temp = line.split("/");
			dependentPath.add(temp);
			line = br.readLine();
		}

	}
}
