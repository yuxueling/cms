/*
 * Copyright 2003-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.cloudht.common.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "url")
public class Url {

	private String ioc;

	public Url() {
	}

	public Url(String ioc) {
		super();
		this.ioc = ioc;
	}

	@XmlElement(name = "ioc")
	public String getIoc() {
		return ioc;
	}

	public void setIoc(String ioc) {
		this.ioc = ioc;
	}
	public String getUrl() {
		return "<url>"
				+ "<loc>"+this.getIoc()+"</loc>"
				+ "<lastmod>2008-07-17</lastmod>"
				+ "<changefreq>weekly</changefreq>"
				+ "<priority>0.9</priority>"
			 + "</url>";
	}

}
