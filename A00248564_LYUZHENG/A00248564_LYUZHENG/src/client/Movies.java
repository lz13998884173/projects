package client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import server.Movie;
import server.Actor;
public class Movies {

	//public static void main(String[] args) {
		JFrame frame;
		private JTextField textFieldId;
		private JTextField textFieldName;
		private JTextField textFieldDirector;
		
		private JTextField textFieldPrice;
		private JTextField textFieldPlace;
		private JTextField textFieldDate;
		
		

		 /////////////////////////// Launch the application.
		 
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						 Movies window = new Movies();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		///////////////////////Create the application.
		
		/**
		 * @wbp.parser.entryPoint
		 */
		public Movies() {initialize();}
			

		 ////////////////////Initialize the contents of the frame.
		 
		private void initialize() {
			frame = new JFrame(" Movies");
			frame.getContentPane().setBackground(Color.CYAN);
			frame.setBounds(0, -14, 729, 420);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblId = new JLabel("Movie Id");
			lblId.setHorizontalAlignment(SwingConstants.RIGHT);
			lblId.setFont(new Font("Georgia", Font.PLAIN, 13));
			lblId.setBounds(10, 58, 92, 22);
			frame.getContentPane().add(lblId);
			
			JLabel lblName = new JLabel("Name");
			lblName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblName.setFont(new Font("Georgia", Font.PLAIN, 13));
			lblName.setBounds(10, 91, 92, 22);
			frame.getContentPane().add(lblName);
			
			JLabel lblDirector = new JLabel("Director");
			lblDirector.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDirector.setFont(new Font("Georgia", Font.PLAIN, 13));
			lblDirector.setBounds(10, 124, 92, 22);
			frame.getContentPane().add(lblDirector);
			
			JLabel lblDate = new JLabel("Date");
			lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDate.setFont(new Font("Georgia", Font.PLAIN, 13));
			lblDate.setBounds(10, 157, 92, 22);
			frame.getContentPane().add(lblDate);
			
			JLabel lblPosition = new JLabel("Price");
			lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPosition.setFont(new Font("Georgia", Font.PLAIN, 13));
			lblPosition.setBounds(10, 190, 92, 22);
			frame.getContentPane().add(lblPosition);
			
			JLabel lblRate = new JLabel("Place");
			lblRate.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRate.setFont(new Font("Georgia", Font.PLAIN, 13));
			lblRate.setBounds(10, 223, 92, 22);
			frame.getContentPane().add(lblRate);
			
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
			
			textFieldDirector = new JTextField();
			textFieldDirector.setFont(new Font("Georgia", Font.PLAIN, 13));
			textFieldDirector.setColumns(10);
			textFieldDirector.setBounds(112, 126, 125, 20);
			frame.getContentPane().add(textFieldDirector);
			
			textFieldPrice = new JTextField();
			textFieldPrice.setFont(new Font("Georgia", Font.PLAIN, 13));
			textFieldPrice.setColumns(10);
			textFieldPrice.setBounds(112, 192, 125, 20);
			frame.getContentPane().add(textFieldPrice);
			
			textFieldPlace = new JTextField();
			textFieldPlace.setFont(new Font("Georgia", Font.PLAIN, 13));
			textFieldPlace.setColumns(10);
			textFieldPlace.setBounds(112, 225, 125, 20);
			frame.getContentPane().add(textFieldPlace);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(343, 27, 334, 218);
			frame.getContentPane().add(scrollPane);
			
			JTextArea textArea = new JTextArea();
			textArea.setBackground(new Color(204, 255, 204));
			scrollPane.setViewportView(textArea);
			textArea.setFont(new Font("Calibri Light", Font.BOLD, 14));
			
			JLabel lblMovies = new JLabel(" Movies");
			lblMovies.setFont(new Font("Georgia", Font.BOLD, 16));
			lblMovies.setBounds(155, 13, 164, 22);
			frame.getContentPane().add(lblMovies);
			
			//ADD NEW MOVIE BUTTON/////////////////////////////////////////////////////////
			JButton btnAdd = new JButton("Add");
			btnAdd.setBackground(new Color(0, 204, 0));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						URI uri = new URIBuilder().setScheme("http")
								.setHost("localhost")
								.setPort(8088)
								.setPath("/A00248564_LYUZHENG/rest/movie")
								.build();
						
						System.out.println(uri.toString());
						HttpPost httpPost = new HttpPost(uri);
						httpPost.setHeader("Accept", "text/html");
						
						CloseableHttpClient httpClient = HttpClients.createDefault();
						
						//POST///////////////POST///////////POST/////////POST//////////POST////////////POST///////////POST
						List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						nameValuePairs.add(new BasicNameValuePair("id", textFieldId.getText()));
						nameValuePairs.add(new BasicNameValuePair("name", textFieldName.getText()));
						nameValuePairs.add(new BasicNameValuePair("director", textFieldDirector.getText()));
						nameValuePairs.add(new BasicNameValuePair("date", textFieldDate.getText()));
						nameValuePairs.add(new BasicNameValuePair("price", textFieldPrice.getText()));
						nameValuePairs.add(new BasicNameValuePair("place", textFieldPlace.getText()));
						
						
						httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						System.out.println("Sending Request  tra ta ta ");
						CloseableHttpResponse response = httpClient.execute(httpPost);
						
						textArea.setText("\t THE MOVIE : " +textFieldId.getText()+" "+textFieldName.getText()+" "+textFieldDirector.getText()+" "+ "SAVED IN DB."
								+"\n"+"==================================================="
								+"\n\t"+textFieldName.getText()+" "										
								+ "\n\t"+textFieldDirector.getText() +" "
								+ "\n\t"+textFieldDate.getText()+" " 
								+ "\n\t"+textFieldPrice.getText()+" "
								+ "\n\t"+textFieldPlace.getText()+" "
								
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
			//ADD NEW MOVIE BUTTON////////////////////////////////////////////////////////
			
			//LIST ALL MOVIE BUTTON/////////////////////////////////////////////////////
			JButton btnList = new JButton("List All");
			btnList.setBackground(new Color(0, 204, 0));
			btnList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CloseableHttpResponse response = null;
					try {					
							URI uri = new URIBuilder().setScheme("http")
									.setHost("localhost").setPort(8088)
									.setPath("/A00248564_LYUZHENG/rest/movie").build();
							
							System.out.println(uri.toString());
							HttpGet httpGet = new HttpGet(uri);
							httpGet.setHeader("Accept", "application/xml");
							
							CloseableHttpClient httpClient = HttpClients.createDefault();
							response = httpClient.execute(httpGet);
							
							HttpEntity entity = response.getEntity();
							String text = getASCIIContentFromEntity(entity);
							List<Movie> MovieList = new ParseMovie().doParseMovie(text);
			
							for (Movie movie : MovieList) {
								
								textArea.append(
										"\n THE MOVIE : " +movie.getId()+"  <<"+movie.getName()+">>  "+movie.getDirector()+"  "+" "+ " BELLOW"
										+"\n"+"=================================================== "
										+"\n\tId: "+ movie.getId()
										+"\n\tName: "+ movie.getName()
										+"\n\tDirector: "+ movie.getDirector()
										+"\n\tDate: "+ movie.getDate()
										+"\n\tPrice: "+ movie.getPrice()
										+"\n\tPlace: "+ movie.getPlace()
										
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
			//LIST ALL MOVIE BUTTON///////////////////////////////////////////////
			
			//SEARCH MOVIE BUTTON/////////////////////////////////////////////////////////
			JButton btnSearchById = new JButton("Search by Id");
			btnSearchById.setBackground(new Color(0, 204, 0));
			btnSearchById.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						CloseableHttpResponse response = null;
						URI uri = new URIBuilder().setScheme("http")
								.setHost("localhost").setPort(8088)
								.setPath("/A00248564_LYUZHENG/rest/movie/"+textFieldId.getText()).build();
						
							System.out.println(uri.toString());
							HttpGet httpGet = new HttpGet(uri);
							httpGet.setHeader("Accept", "application/xml");
							
							CloseableHttpClient httpClient = HttpClients.createDefault();
							response = httpClient.execute(httpGet);
							
							HttpEntity entity = response.getEntity();
							System.out.println(entity);
							String text = getASCIIContentFromEntity(entity);
							
							List<Movie> movieList = new ParseMovie().doParseMovie(text);
							for (int index = 0; index < movieList.size(); index++) {
								
								Movie movie = movieList.get(index);
								System.out.println("Id: " + movie.getId());
								System.out.println("Name: " + movie.getName());
								System.out.println("Director: "+ movie.getDirector());
								System.out.println("Date: " + movie.getDate());
								System.out.println("Price: " + movie.getPrice());
								System.out.println("Place: " + movie.getPlace());
								
								
								textFieldName.setText(""+movie.getName());
								textFieldDirector.setText(""+movie.getDirector());
				
								textFieldDate.setText(""+movie.getDate());
								textFieldPrice.setText(""+movie.getPrice());
								textFieldPlace.setText(""+movie.getPlace());
								
								
								textArea.setText("\t THE MOVIE : " +textFieldId.getText()+" "+ "FOUND FROM THE DB."
										+"\n"+"=================================================== "
										+"\n\t"+textFieldName.getText()+" "										
										+ "\n\t"+textFieldDirector.getText() +" "
										+ "\n\t"+textFieldDate.getText()+" " 
										+ "\n\t"+textFieldPrice.getText()+" "
										+ "\n\t"+textFieldPlace.getText()+" "
																		
										+"\n"+"=================================================== "
										);	
							//	if(textFieldId!=id){textArea.setText("\t THE MOVIE NOT EXIST");}
						
							} response.close();	
					} catch (Exception el) {
						el.printStackTrace();
					}	
				
				}
			});
			btnSearchById.setFont(new Font("Georgia", Font.BOLD, 13));
			btnSearchById.setBounds(10, 300, 125, 25);
			frame.getContentPane().add(btnSearchById);
			//SEARCH MOVIE BUTTON/////////////////////////////////////////////////////////
			
			//DELETE MOVIE BUTTON/////////////////////////////////////////////////////////
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
								.setPath("/A00248564_LYUZHENG/rest/movie/"
										+textFieldId.getText()).build();
						
						System.out.println(uri.toString());
						HttpDelete httpDelete = new HttpDelete(uri);
						httpDelete.setHeader("Aceept","text/html");
						CloseableHttpClient httpClient = HttpClients.createDefault();
						response = httpClient.execute(httpDelete);
						textArea.setText("\t THE MOVIE : " +textFieldId.getText()+" "+ "WAS DELITED FROM THE DB."
								+"\n"+"==================================================="
								+"\n\t"+textFieldName.getText()+" "										
								+ "\n\t"+textFieldDirector.getText() +" "
								+ "\n\t"+textFieldDate.getText()+" " 
								+ "\n\t"+textFieldPrice.getText()+" "
								+ "\n\t"+textFieldPlace.getText()+" "
								
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
			//DELETE MOVIE BUTTON//////////////////////////////////////////////////////////////
			
			//UPDATE MOVIE BUTTON//////////////////////////////////////////////////////////////
			JButton btnUpdate = new JButton("Update");
			btnUpdate.setBackground(new Color(0, 204, 0));
			btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CloseableHttpResponse response = null;
					try {
						URI uri = new URIBuilder().setScheme("http")
								.setHost("localhost").setPort(8088)
								.setPath("/A00248564_LYUZHENG/rest/movie/"
								+textFieldId.getText()).build();
						
						System.out.println(uri.toString());
						HttpPut httpPut = new HttpPut(uri);
						httpPut.setHeader("Accept", "text/html");
						
						CloseableHttpClient httpClient = HttpClients.createDefault();
						
						List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
						nameValuePairs.add(new BasicNameValuePair("id", textFieldId.getText()));
						nameValuePairs.add(new BasicNameValuePair("name", textFieldName.getText()));
						nameValuePairs.add(new BasicNameValuePair("director", textFieldDirector.getText()));
						nameValuePairs.add(new BasicNameValuePair("date", textFieldDate.getText()));		
						nameValuePairs.add(new BasicNameValuePair("price", textFieldPrice.getText()));
						nameValuePairs.add(new BasicNameValuePair("place", textFieldPlace.getText()));
						
						httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						System.out.println("Sending PUT Request");
						response = httpClient.execute(httpPut);	
						
						textArea.setText("\t THE MOVIE : " +textFieldId.getText()+" "+ " UPDATED."
						+"\n"+"==================================================="
						+"\n\t"+textFieldName.getText()+" "										
						+ "\n\t"+textFieldDirector.getText() +" "
						+ "\n\t"+textFieldDate.getText()+" " 
						+ "\n\t"+textFieldPrice.getText()+" "
						+ "\n\t"+textFieldPlace.getText()+" "
						
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
							textFieldDirector.setText("");
							textFieldDate.setText("");
							textFieldPrice.setText("");
							textFieldPlace.setText("");
							
							textArea.setText("");
						}
					});
					btnClear.setFont(new Font("Georgia", Font.BOLD, 13));
					btnClear.setBounds(30, 335, 115, 25);
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
			btnNewButton.setBounds(155, 335, 125, 25);
			frame.getContentPane().add(btnNewButton);
			
			JButton btnMovies = new JButton("Actors");
			btnMovies.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {//GO TO MovieS TABLE
					Object target = e.getSource();
					if (target == btnMovies) {
						Actors clt = new Actors();
						clt.frame.setVisible(true); 
						//Code to close current window
						frame.setVisible(false); 
						frame.dispose(); 
						
						
					}
				}
			});
			btnMovies.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnMovies.setBackground(new Color(204, 204, 0));
			btnMovies.setBounds(302, 335, 196, 25);
			frame.getContentPane().add(btnMovies);
			
			textFieldDate = new JTextField();
			textFieldDate.setFont(new Font("Georgia", Font.PLAIN, 13));
			textFieldDate.setColumns(10);
			textFieldDate.setBounds(112, 157, 125, 20);
			frame.getContentPane().add(textFieldDate);
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
