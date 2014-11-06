/**   
* @Project: devops
* @Title: LDAPUser.java
* @Package devops.common
* @Description: TODO
* @author sunyu
* @date Nov 5, 2014 3:50:21 PM
* @version V1.0   
*/
package devops.common;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 * @author sunyu
 *
 */
public class LDAPUser {
	private String username;
	private String password;
	private String userDn;
	
	private String commonname;
	private String givenname;
	private String surname;
	private String title;
	private String mail;
	private String description;
	private String uid;
	
	public LdapContext getConnectionFromPool() throws NamingException{
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://114.80.90.108:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL,"cn=root,dc=madhouse,dc=cn");
		env.put(Context.SECURITY_CREDENTIALS,"MF4C3Szm");
		
		env.put("com.sun.jndi.ldap.connect.pool", "true");
		env.put("java.naming.referral", "follow");
		return new InitialLdapContext(env, null);
	}
	
	public String getDnFromLDAP(){
		Control[] connCtls = new Control[]{};
		LdapContext ldapctx = null;
		
		try {
			ldapctx = getConnectionFromPool();
			SearchControls searchCtls = new SearchControls();
			String searchBase = "dc=madhouse,dc=cn";  
			String searchFilter = "uid=" + this.username;  
			
			String returnedAtts[] = { "url", "whenChanged", "employeeID", "name",  
					"userPrincipalName", "physicalDeliveryOfficeName",  
					"departmentNumber", "telephoneNumber", "homePhone", "mobile",  
					"department", "sAMAccountName", "whenChanged", "mail", "commonName","uid" }; 
			
			searchCtls.setReturningAttributes(returnedAtts);	
			
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			
			NamingEnumeration answer = ldapctx.search(searchBase, searchFilter, searchCtls);
			System.out.println(answer);
			if (answer.hasMoreElements()){
				SearchResult sr = (SearchResult) answer.next();
				this.userDn = sr.getName() + "," + searchBase;
				
				int totalResults = 0;
				Attributes Attrs = sr.getAttributes();  
				
				for (NamingEnumeration ne = Attrs.getAll(); ne.hasMore();) {  
					Attribute Attr = (Attribute) ne.next();  
					System.out.println("AttributeID=" + Attr.getID().toString());  
					
					String value = null;
					// 读取属性值  
					for (NamingEnumeration e = Attr.getAll(); e.hasMore(); totalResults++) {  
						String user = e.next().toString(); // 接受循环遍历读取的userPrincipalName用户属性  
						System.out.println(user);  
						value = user;
					}
					if(Attr.getID().toString().equals("mail")){
						this.mail = value;
					}
					if(Attr.getID().toString().equals("cn")){
						this.commonname = value;
					}
					
				}
				// System.out.println(" ---------------");  
				// // 读取属性值  
				// Enumeration values = Attr.getAll();  
				// if (values != null) { // 迭代  
				// while (values.hasMoreElements()) {  
				// System.out.println(" 2AttributeValues="  
				// + values.nextElement());  
				// }  
				// }  
				// System.out.println(" ---------------");  
				
				
				
				System.out.print(this.userDn);
				return sr.getName() + "," + searchBase; 
			}else{
				return "Not Found"; 
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "Error"; 
		}
	}
	
	public boolean authenticate(){
		Control[] connCtls = new Control[]{};
		LdapContext ldapctx = null;
		try {
			ldapctx = getConnectionFromPool();
			getDnFromLDAP();
			System.out.print(this.userDn);
			ldapctx.addToEnvironment(Context.SECURITY_PRINCIPAL, this.userDn);
			ldapctx.addToEnvironment(Context.SECURITY_CREDENTIALS, this.password);
			ldapctx.reconnect(connCtls);
			return true;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the userDn
	 */
	public String getUserDn() {
		return userDn;
	}
	/**
	 * @param userDn the userDn to set
	 */
	public void setUserDn(String userDn) {
		this.userDn = userDn;
	}
	/**
	 * @return the commonname
	 */
	public String getCommonname() {
		return commonname;
	}
	/**
	 * @param commonname the commonname to set
	 */
	public void setCommonname(String commonname) {
		this.commonname = commonname;
	}
	/**
	 * @return the givenname
	 */
	public String getGivenname() {
		return givenname;
	}
	/**
	 * @param givenname the givenname to set
	 */
	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the email
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param email the email to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
}
