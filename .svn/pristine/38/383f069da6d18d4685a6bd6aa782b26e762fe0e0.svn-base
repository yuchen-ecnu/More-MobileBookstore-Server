package com.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyConfig {
	private Properties mProperties = null;

	public void LoadAppConfiguration(String strConfigurationFile) throws FileNotFoundException, IOException {
		// create a property object whose parent properties are the standard
		// system properties
		Properties oProperties = new Properties(System.getProperties());
		// now load in our (child) properties from file
		oProperties.load(new BufferedInputStream(new FileInputStream(strConfigurationFile)));

		mProperties = oProperties;
	}

	public boolean updatePropertiesFile(String aFile, String aKey, String aValue) {
		try {
			File f = new File(aFile);
			boolean res = false;
			if (f.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String outstr = "";
				String line = "";

				while ((line = br.readLine()) != null) {
					if (line == "") // 如果为空
					{
						outstr += "\r\n";
						continue;
					}
					if (line.startsWith("#")) // 如果为注释
					{
						outstr += line + "\r\n";
						continue;
					}
					if (line.trim().startsWith(aKey)) {
						String[] keyandvalue = line.split("=");

						outstr += keyandvalue[0].toString() + "=" + aValue.toString() + "\r\n";

						res = true;
						continue;
					}

					outstr += line + "\r\n";

				}
				// System.out.println(outstr);
				if (res) {

					FileWriter fw = new FileWriter(aFile, false);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(outstr);

					bw.close();
					fw.close();
					return true;

				}

			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public void setProperty(String aKey, String aValue) {
		if (aKey != null && aValue != null) {
			mProperties.put(aKey, aValue);
		}
	}

	public String getProperty(String aKey) {
		if (mProperties.containsKey(aKey)) {
			return mProperties.getProperty(aKey);
		} else {
			return null;
		}

	}

	public void removeProperty(String aKey) {
		if (mProperties.containsKey(aKey)) {
			mProperties.remove(aKey);
		}
	}

	public Properties getAllProperties() {
		return this.mProperties;
	}

	public void fillProperties(Properties aProperties) {
		mProperties = aProperties;
	}

}
