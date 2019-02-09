package client;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import server.Movie;
import server.Actor;

import javax.swing.JTextArea;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Actors {

	JFrame frame;
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldBirthday;
	private JTextField textFieldPhone;
	private JTextField textFieldEmail;
	private JTextField textFieldAddress;
	

	
	

	 /////////////////////////// Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Actors window = new Actors();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	///////////////////////Create the application.
	
	public Actors() {initialize();}
		

	 ////////////////////Initialize the contents of the frame.
	 
	private void initialize() {
		frame = new JFrame(" Actors");
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.setBounds(0, -14, 729, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("Actor Id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblId.setBounds(10, 58, 92, 22);
		frame.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblName.setBounds(10, 91, 92, 22);
		frame.getContentPane().add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblSurname.setBounds(10, 124, 92, 22);
		frame.getContentPane().add(lblSurname);
		
		JLabel lblDepartment = new JLabel("Birthday");
		lblDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartment.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblDepartment.setBounds(10, 157, 92, 22);
		frame.getContentPane().add(lblDepartment);
		
		JLabel lblPosition = new JLabel("Phone");
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblPosition.setBounds(10, 190, 92, 22);
		frame.getContentPane().add(lblPosition);
		
		JLabel lblRate = new JLabel("Email");
		lblRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRate.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblRate.setBounds(10, 223, 92, 22);
		frame.getContentPane().add(lblRate);
		
		JLabel lblHours = new JLabel("Address");
		lblHours.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHours.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblHours.setBounds(10, 256, 92, 22);
		frame.getContentPane().add(lblHours);
		
		textFieldId = new JTextField();
		textFieldId.setToolTipText("");
		textFieldId.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldId.setColumns(10);
		textFieldId.setBounds(112, 60, 125, 20);
		frame.getContentPane().add(textFieldId);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldName.setColumns(10);
		textFieldName.setBounds(112, 93, 125, 20);
		frame.getContentPane().add(textFieldName);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(112, 126, 125, 20);
		frame.getContentPane().add(textFieldSurname);
		
		textFieldBirthday = new JTextField();
		textFieldBirthday.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldBirthday.setColumns(10);
		textFieldBirthday.setBounds(112, 159, 125, 20);
		frame.getContentPane().add(textFieldBirthday);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(112, 192, 125, 20);
		frame.getContentPane().add(textFieldPhone);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(112, 225, 125, 20);
		frame.getContentPane().add(textFieldEmail);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(112, 258, 125, 20);
		frame.getContentPane().add(textFieldAddress);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 60, 430, 218);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(204, 255, 204));
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblActors = new JLabel(" Actors");
		lblActors.setFont(new Font("Georgia", Font.BOLD, 16));
		lblActors.setBounds(155, 13, 164, 22);
		frame.getContentPane().add(lblActors);
		
		//ADD NEW Actor BUTTON/////////////////////////////////////////////////////////
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(0, 204, 0));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					URI uri = new URIBuilder().setScheme("http")
							.setHost("localhost")
							.setPort(8088)
							.setPath("/A00248564_LYUZHENG/rest/Actor")
							.build();
					
					System.out.println(uri.toString());
					HttpPost httpPost = new HttpPost(uri);
					httpPost.setHeader("Accept", "text/html");
					
					CloseableHttpClient httpClient = HttpClients.createDefault();
					
					//POST///////////////POST///////////POST/////////POST//////////POST////////////POST///////////POST
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
					nameValuePairs.add(new BasicNameValuePair("id", textFieldId.getText()));
					nameValuePairs.add(new BasicNameValuePair("fname", textFieldName.getText()));
					nameValuePairs.add(new BasicNameValuePair("sname", textFieldSurname.getText()));
					nameValuePairs.add(new BasicNameValuePair("birthday", textFieldBirthday.getText()));
					nameValuePairs.add(new BasicNameValuePair("phone", textFieldPhone.getText()));
					nameValuePairs.add(new BasicNameValuePair("email", textFieldEmail.getText()));
					nameValuePairs.add(new BasicNameValuePair("address", textFieldAddress.getText()));
					
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					System.out.println("Sending Request");
					CloseableHttpResponse response = httpClient.execute(httpPost);
					
					textArea.setText("\t THE ACTOR : " +textFieldId.getText()+" "+textFieldName.getText()+" "+textFieldSurname.getText()+" "+ "SAVED IN DB."
							+"\n"+"==================================================="
							+"\n\t"+textFieldName.getText()+" "										
							+ "\n\t"+textFieldSurname.getText() +" "
							+ "\n\t"+textFieldBirthday.getText()+" " 
							+ "\n\t"+textFieldPhone.getText()+" "
							+ "\n\t"+textFieldEmail.getText()+" "
							+ "\n\t"+textFieldAddress.getText()	
							+"\n"+"=================================================== "
							);		
					String text;
					try {
						HttpEntity entity=response.getEntity();
						text=getASCIIContentFromEntity(entity);
					} finally {
						response.close();
					}
					System.out.println(text);
					
				} catch (Exception e) {
					e.printStackTrace();
					}
			}
		});
		btnAdd.setFont(new Font("Georgia", Font.BOLD, 13));
		btnAdd.setBounds(155, 300, 125, 25);
		frame.getContentPane().add(btnAdd);
		//ADD NEW Actor BUTTON////////////////////////////////////////////////////////
		
		//LIST ALL ActorS BUTTON/////////////////////////////////////////////////////
		JButton btnList = new JButton("List All");
		btnList.setBackground(new Color(0, 204, 0));
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseableHttpResponse response = null;
				try {					
						URI uri = new URIBuilder().setScheme("http")
								.setHost("localhost").setPort(8088)
								.setPath("/A00248564_LYUZHENG/rest/Actor").build();
						
						System.out.println(uri.toString());
						HttpGet httpGet = new HttpGet(uri);
						httpGet.setHeader("Accept", "application/xml");
						
						CloseableHttpClient httpClient = HttpClients.createDefault();
						response = httpClient.execute(httpGet);
						
						HttpEntity entity = response.getEntity();
						String text = getASCIIContentFromEntity(entity);
						//System.out.println("Text = "+text);
						List<Actor> ActorList = new ParseActor().doParseActor(text);
						for (Actor actors : ActorList) {
							textArea.append(
									"\n THE ACTOR : " +actors.getId()+"  "+actors.getFname()+"  "+actors.getSname()+"  "+" "+ " BELLOW"
									+"\n"+"=================================================== "
									+"\n\tId: "+ actors.getId()
									+"\n\tName: "+ actors.getFname()
									+"\n\tSurname: "+ actors.getSname()
									+"\n\tBirthday: "+ actors.getBirthday()
									+"\n\tPhone: "+ actors.getPhone()
									+"\n\tEmail: "+ actors.getEmail()
									+"\n\tAddress: "+ actors.getAddress()
									+"\n"+"=================================================== "
									);	
							
						}						
						response.close();
					
				} catch (Exception el) {
					el.printStackTrace();
				}
				
			}
		});
		btnList.setFont(new Font("Georgia", Font.BOLD, 13));
		btnList.setBounds(419, 300, 125, 25);
		frame.getContentPane().add(btnList);
		//LIST ALL ActorS BUTTON///////////////////////////////////////////////
		
		//SEARCH BUTTON/////////////////////////////////////////////////////////
		JButton btnSearchById = new JButton("Search by Id");
		btnSearchById.setBackground(new Color(0, 204, 0));
		btnSearchById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CloseableHttpResponse response = null;
					URI uri = new URIBuilder().setScheme("http")
							.setHost("localhost").setPort(8088)
							.setPath("/A00248564_LYUZHENG/rest/Actor/"+textFieldId.getText()).build();
					
						System.out.println(uri.toString());
						HttpGet httpGet = new HttpGet(uri);
						httpGet.setHeader("Accept", "application/xml");
						
						CloseableHttpClient httpClient = HttpClients.createDefault();
						response = httpClient.execute(httpGet);
						
						HttpEntity entity = response.getEntity();
						System.out.println(entity);
						String text = getASCIIContentFromEntity(entity);
						
						List<Actor> ActorList = new ParseActor().doParseActor(text);
						for (int index = 0; index < ActorList.size(); index++) {
							
							Actor actors = ActorList.get(index);
							System.out.println("Id: " + actors.getId());
							System.out.println("Name: " + actors.getFname());
							System.out.println("Surname: "+ actors.getSname());
							System.out.println("Birthday: " + actors.getBirthday());
							System.out.println("Phone: " + actors.getPhone());
							System.out.println("Email: " + actors.getEmail());
							System.out.println("Address: " + actors.getAddress());
							
							textFieldName.setText(""+actors.getFname());
							textFieldSurname.setText(""+actors.getSname());
							textFieldBirthday.setText(""+actors.getBirthday());
							textFieldPhone.setText(""+actors.getPhone());
							textFieldEmail.setText(""+actors.getEmail());
							textFieldAddress.setText(""+actors.getAddress());
							
							textArea.setText("\t THE ACTOR : " +textFieldId.getText()+" "+ "FOUND FROM THE DB."
									+"\n"+"=================================================== "
									+"\n\t"+textFieldName.getText()+" "										
									+ "\n\t"+textFieldSurname.getText() +" "
									+ "\n\t"+textFieldBirthday.getText()+" " 
									+ "\n\t"+textFieldPhone.getText()+" "
									+ "\n\t"+textFieldEmail.getText()+" "
									+ "\n\t"+textFieldAddress.getText()										
									+"\n"+"=================================================== "
									);	
						//	if(textFieldId!=id){textArea.setText("\t THE ACTOR NOT EXIST");}
					
						} response.close();	
				} catch (Exception el) {
					el.printStackTrace();
				}	
			
			}
		});
		btnSearchById.setFont(new Font("Georgia", Font.BOLD, 13));
		btnSearchById.setBounds(10, 300, 125, 25);
		frame.getContentPane().add(btnSearchById);
		//SEARCH BUTTON/////////////////////////////////////////////////////////
		
		//DELETE BUTTON/////////////////////////////////////////////////////////
		JButton btnDelete = new JButton("Delete\r\n");
		btnDelete.setBackground(new Color(0, 204, 0));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseableHttpResponse response = null;
				HttpEntity entity=null;
				try {
					URI uri = new URIBuilder().setScheme("http")
							.setHost("localhost").setPort(8088)
							.setPath("/A00248564_LYUZHENG/rest/Actor/"
									+textFieldId.getText()).build();
					
					System.out.println(uri.toString());
					HttpDelete httpDelete = new HttpDelete(uri);
					httpDelete.setHeader("Aceept","text/html");
					CloseableHttpClient httpClient = HttpClients.createDefault();
					response = httpClient.execute(httpDelete);
					textArea.setText("\t THE ACTOR : " +textFieldId.getText()+" "+ "WAS DELITED FROM THE DB."
							+"\n"+"==================================================="
							+"\n\t"+textFieldName.getText()+" "										
							+ "\n\t"+textFieldSurname.getText() +" "
							+ "\n\t"+textFieldBirthday.getText()+" " 
							+ "\n\t"+textFieldPhone.getText()+" "
							+ "\n\t"+textFieldEmail.getText()+" "
							+ "\n\t"+textFieldAddress.getText()	
							+"\n"+"=================================================== "
							);		
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						response.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
			}
		});
		btnDelete.setBounds(562, 300, 115, 25);
		frame.getContentPane().add(btnDelete);
		//DELETE BUTTON//////////////////////////////////////////////////////////////
		
		//UPDATE BUTTON//////////////////////////////////////////////////////////////
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(0, 204, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseableHttpResponse response = null;
				try {
					URI uri = new URIBuilder().setScheme("http")
							.setHost("localhost").setPort(8088)
							.setPath("/A00248564_LYUZHENG/rest/Actor/"
							+textFieldId.getText()).build();
					
					System.out.println(uri.toString());
					HttpPut httpPut = new HttpPut(uri);
					httpPut.setHeader("Accept", "text/html");
					
					CloseableHttpClient httpClient = HttpClients.createDefault();
					
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
					nameValuePairs.add(new BasicNameValuePair("id", textFieldId.getText()));
					nameValuePairs.add(new BasicNameValuePair("fname", textFieldName.getText()));
					nameValuePairs.add(new BasicNameValuePair("sname", textFieldSurname.getText()));
					nameValuePairs.add(new BasicNameValuePair("birthday", textFieldBirthday.getText()));		
					nameValuePairs.add(new BasicNameValuePair("phone", textFieldPhone.getText()));
					nameValuePairs.add(new BasicNameValuePair("email", textFieldEmail.getText()));
					nameValuePairs.add(new BasicNameValuePair("address", textFieldAddress.getText()));
					httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					System.out.println("Sending PUT Request");
					response = httpClient.execute(httpPut);	
					
					textArea.setText("\t THE ACTOR : " +textFieldId.getText()+" "+ " UPDATED."
					+"\n"+"==================================================="
					+"\n\t"+textFieldName.getText()+" "										
					+ "\n\t"+textFieldSurname.getText() +" "
					+ "\n\t"+textFieldBirthday.getText()+" " 
					+ "\n\t"+textFieldPhone.getText()+" "
					+ "\n\t"+textFieldEmail.getText()+" "
					+ "\n\t"+textFieldAddress.getText()	
					+"\n"+"==================================================="
					);
					
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					} catch (ClientProtocolException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						try {
							response.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
			}
		});
		btnUpdate.setBounds(292, 300, 115, 25);
		frame.getContentPane().add(btnUpdate);
		//UPDATE BUTTON//////////////////////////////////////////////////////////////////
		
		//CLEAR BUTTON/////////////////////////////////////////////////////////
				JButton btnClear = new JButton("Clear");
				btnClear.setBackground(new Color(255, 102, 102));
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textFieldId.setText("");
						textFieldName.setText("");
						textFieldSurname.setText("");
						textFieldBirthday.setText("");
						textFieldPhone.setText("");
						textFieldEmail.setText("");
						textFieldAddress.setText("");
						textArea.setText("");
					}
				});
				btnClear.setFont(new Font("Georgia", Font.BOLD, 13));
				btnClear.setBounds(10, 335, 125, 25);
				frame.getContentPane().add(btnClear);	
		//CLEAR BUTTON//////////////////////////////////////////////////////////
		
		//EXIT BUTTON////////////////////////////////////////////////////////////////////
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		btnNewButton.setBackground(new Color(255, 102, 102));
		btnNewButton.setFont(new Font("Georgia", Font.BOLD, 13));
		btnNewButton.setBounds(155, 335, 134, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnLecturer = new JButton("Movies");
		btnLecturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//GO TO LECTURERS TABLE
				
				Object target = e.getSource();
				if (target == btnLecturer) {
					Movies dpa = new Movies();
					dpa.frame.setVisible(true); 
					//Code to close current window
					frame.setVisible(false); 
					frame.dispose(); 
					
					
				}
				
			}
		});
		btnLecturer.setBackground(new Color(204, 204, 0));
		btnLecturer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLecturer.setBounds(302, 335, 155, 25);
		frame.getContentPane().add(btnLecturer);
		}
		//EXIT BUTTON////////////////////////////////////////////////////////////////////
	
	static String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();		
	}
}
