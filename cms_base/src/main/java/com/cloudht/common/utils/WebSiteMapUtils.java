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
package com.cloudht.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import com.cloudht.common.domain.Sitemap;
import com.cloudht.common.domain.Url;

public class WebSiteMapUtils {
	/**
	 * 谷歌页头
	 */
	public final static String URLSET_START = "<?xml version='1.0' encoding='UTF-8'?>\n"
			+ "<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "         xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\"\n"
			+ "         xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n";
	/**
	 * 谷歌页尾
	 */
	public final static String URLSET_END = "\n</urlset>";
	protected final static String SITEMAPINDEX_START = "<?xml version='1.0' encoding='UTF-8'?>\n"
			+ "<sitemapindex xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
			+ "         xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/siteindex.xsd\"\n"
			+ "         xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n";
	public final static String SITEMAPINDEX_END = "\n</sitemapindex>";
	public static void writeSitemapIndex(Writer writer, Iterator<? extends Sitemap> mappings) throws IOException {
		writeXml(writer, SITEMAPINDEX_START, mappings, SITEMAPINDEX_END);
	}

	public static long writeUrlset(Writer writer, Iterator<Url> urls) throws IOException {
		return writeXml(writer, URLSET_START, urls, URLSET_END);
	}

	public static long writeXml(Writer writer, String start, Iterator<?> it, String end) throws IOException {
		writer.write(start);//输入流输入文件头
		long count = writeSubtree(writer, it);
		writer.write(end);//输入流输入文件尾
		return count;
	}

	public static long writeSubtree(Writer writer, Iterator<?> it) throws IOException {
		long size = 0;
		Marshaller m;
		try {
			JAXBContext jc = JAXBContext.newInstance(Sitemap.class, Url.class);
			m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FRAGMENT, true);
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (PropertyException e) {
			throw new DataBindingException(e);
		} catch (JAXBException e) {
			throw new DataBindingException(e);
		}

		boolean first = true;
		while (it.hasNext()) {
			if (first)
				first = false;
			else
				writer.write("\n");
			try {
				m.marshal(it.next(), writer);
			} catch (JAXBException e) {
				throw new IOException(e);
			}
			size++;
		}
		return size;
	}

	public static void main(String[] args) {
		try {
			//创建一个单列集合，把所有网页对象添加进去
			Collection<Sitemap> s = new ArrayList<Sitemap>();
			s.add(new Sitemap("http://www.example.com/sitemap1.xml.gz"));
			s.add(new Sitemap("http://www.example.com/sitemap2.xml.gz"));
			s.add(new Sitemap("http://www.example.com/sitemap3.xml.gz"));
			s.add(new Sitemap("http://www.example.com/sitemap4.xml.gz"));
			//创建一个字符输入流
			Writer wt = new PrintWriter(System.out);
			WebSiteMapUtils.writeSitemapIndex(wt, s.iterator());
			Collection<Url> s2=new ArrayList<Url>();
			s2.add(new Url("http://www.example.com/sitemap1.xml.gz"));
			Iterator<Url> iterator = s2.iterator();
			WebSiteMapUtils.writeUrlset(wt,iterator);
			wt.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
