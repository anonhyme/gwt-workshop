package gwtws.mvp.server;

import gwtws.mvp.shared.pojo.Contact;
import gwtws.mvp.shared.pojo.ContactDetails;

import java.util.ArrayList;

public interface ContactDao {

	public abstract Contact addContact(Contact contact);

	public abstract Contact updateContact(Contact contact);

	public abstract Boolean deleteContact(String id);

	public abstract ArrayList<ContactDetails> deleteContacts(ArrayList<String> ids);

	public abstract ArrayList<ContactDetails> getContactDetails();

	public abstract Contact getContact(String id);

}