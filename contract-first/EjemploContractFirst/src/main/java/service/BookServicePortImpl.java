package com.hascode.tutorial.ws.service;

import java.util.GregorianCalendar;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

@WebService(endpointInterface = "com.hascode.tutorial.ws.service.BookServicePortType")
public class BookServicePortImpl implements BookServicePortType {

	@Override
	public BookServiceResponseType fetchBooks(
			final BookServiceRequestType bookServiceRequest) {
		final BookServiceResponseType response = new BookServiceResponseType();
		for (int i = 0; i < bookServiceRequest.getLimit(); i++) {
			final BookType book = new BookType();
			book.setAuthor("Elvis " + i);
			try {
				book.setPublished(DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(
								new GregorianCalendar(2011, 8, 14)));
			} catch (DatatypeConfigurationException e) {
			}
			book.setTitle("Programming Java Edition #" + i);
			response.getBook().add(book);
		}
		return response;
	}

}
