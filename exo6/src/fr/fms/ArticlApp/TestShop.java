package fr.fms.ArticlApp;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import fr.fms.job.ArticleJob;
import fr.fms.job.ArticleJobImpl;
import fr.fms.job.CategoryJob;
import fr.fms.job.CategoryJobImpl;
import fr.fms.job.OrderJob;
import fr.fms.job.OrderJobImpl;
import fr.fms.job.OrderLineJob;
import fr.fms.job.OrderLineJobImpl;
import fr.fms.job.UserJob;
import fr.fms.job.UserJobImpl;
import fr.fms.security.ValidateLogin;
import fr.frm.dao.Dao;
import fr.frm.dao.DaoFactory;
import fr.frm.entities.Article;
import fr.frm.entities.Category;
import fr.frm.entities.Order;
import fr.frm.entities.OrderLine;
import fr.frm.entities.Utilisateur;

public class TestShop {

	public static Scanner scanner = new Scanner(System.in);
	public static ValidateLogin validateLogin = new ValidateLogin();
	public static ArticleJob articleJob = new ArticleJobImpl();
	public static CategoryJob categoryJob = new CategoryJobImpl();
	public static OrderLineJob orderLineJob = new OrderLineJobImpl();
	public static OrderJob orderJob = new OrderJobImpl();
	public static UserJob userJob = new UserJobImpl();

	/***************************** Factory *********************************/
	public static DaoFactory daoFactory = new DaoFactory();
	public static Dao<Category> catDao = DaoFactory.getCategoryDao();
	public static Dao<Article> artDao = DaoFactory.getArticleDao();
	public static Dao<Utilisateur> daoUser = DaoFactory.getUserDao();

	/*********************************************************************/
	public static void main(String[] args) {

//		List<Category> categories;
//		List<Article> articles;
//		try {
//			categories = catDao.readAllObjs();
//			articles = artDao.getArticlesByCategory(1);
//			categories.forEach(c -> {
//				System.out.println(c.toString());
//			});
//			articles.forEach(a -> {
//				System.out.println("\n" + a.toString());
//			});
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		System.out.println("---------------------------QUESTION 13--------------------");
		while (true) {
			System.out.println("Connectez-vous afin d'accéder à la application\nEntrez votre NON D'UTILISATEUR :");
			String userName = scanner.next();
			System.out.println("Entrez votre mot de pass:");
			String password = scanner.next();
			Utilisateur utilisateur = new Utilisateur(0, userName, password);
			if (validateLogin.validateObjLogin(utilisateur))
				storeMenu(daoUser.getUserId(userName));
		}
	}

	private static void storeMenu(int userId) {
		int menuChoice = -1;
		while (menuChoice != 0) { // (0) to exit menu
			try {
				System.out.println("-------------------------- STORE MENU --------------------------" + "\n"
						+ "Pour afficher TOUS les ARTICLES,                       enter (1)\n"
						+ "Pour afficher les articles PAR CATEGORIE,              enter (2)\n"
						+ "Pour AJOUTER un article au PANIER,                     enter (3)\n"
						+ "Pour SUPPRIMER un article dans le PANIER               enter (4)\n"
						+ "Pour AFFICHER et VALIDER le PANIER,                    enter (5)\n"
						+ "Pour recuperer la FACTURE-COMMANDE (admin),            enter (6)\n"
						+ "SORTIR de l'application,                               enter (0)\n"
						+ "----------------------------------------------------------------");

				/** Only integer entries accepted */
				menuChoice = (int) getPositiveIntegerInput(scanner, "\nFaite votre choix!");
				switch (menuChoice) {
				case 1:
					showAllArticles(articleJob.getAllArticles());
					break;

				case 2:
					showAllCategories(categoryJob.getAllCategories());
					int idCategory = (int) getPositiveIntegerInput(scanner, "\nEntrez l'ID de la CATEGORY concernée!");
					showAllArticles(articleJob.getArticlesByCategory(idCategory));
					break;

				case 3:
					int idArticle = (int) getPositiveIntegerInput(scanner,
							"\nEntrez l'ID de l'article que vous souhaitez!");
					articleJob.addArticleToBucket(idArticle);
					break;

				case 4:
					showMyBucket(articleJob.getMyBucket());
					int idArticle1 = (int) getPositiveIntegerInput(scanner,
							"\nEntrez l'ID de l'article que vous souhaitez RETIRER!");
					articleJob.removeArticleFromBucket(idArticle1);
					break;

				case 5:
					validateBucket(articleJob.getMyBucket(), userId);
					break;
					
				case 6:
					int orderId = (int) getPositiveIntegerInput(scanner, "\nEntrez l'ID de la commande concernée!");
					getInvoice(orderId);
					break;
					
				case 0:
					menuChoice = 0;
					break;

				default:
					System.out.println("Wrong entry: ONLY INTEGERS ENTRIES ( 0 to 5)");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void getInvoice(int orderId) {
		List<String[]> invoiceElement = orderJob.getInvoiceElements(orderId);
		for(String[] array : invoiceElement) {
			   System.out.println(Arrays.toString(array));
			}
	}

	private static void showAllCategories(List<Category> categories) {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("|ID   | NAME                          |DESCRIPTION                          |");
		System.out.println("------+---------------------------------------------------------------------+");//

		/* * Display the table body: Browse the training HashMap */
		for (Category a : categories) {
			System.out.println(String.format("|%-5s|%-31s|%-37s|", a.getId(), a.getName(), a.getDescription()));
			System.out.println("------+---------------------------------------------------------------------+");
		}

	}

	/**
	 * 
	 * @param articles
	 */
	private static void showAllArticles(List<Article> articles) {
		// Build the table header
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("|ID   |DESCRIPTION                         |BRAND                |PRICE (€) |");
		System.out.println("------+------------------------------------+---------------------+----------+");//

		/* * Display the table body: Browse the training HashMap */
		for (Article a : articles) {
			System.out.println(String.format("|%-5s|%-36s|%-21s|%-10s|", a.getId(), a.getDescription(), a.getBrand(),
					a.getPrice()));
			System.out.println("------+------------------------------------+---------------------+----------+");
		}

	}

	/**
	 * 
	 * @param myBucket
	 * @param userId 
	 */
	private static void validateBucket(Map<Integer, Article> myBucket, int userId) {
		// TODO Auto-showMyBucket(articleJob.getMyBucket());
		if (articleJob.getMyBucket().isEmpty())
			System.out.println("\nYour bucket si empty!\n");
		else {
			showMyBucket(articleJob.getMyBucket());
			int validate = getPositiveOneOrTwo(scanner, "Voulez-vous VALIDER votre panier:  ?(1:oui/ 2:non)");
			int confirm = 0;
			if (validate == 1) {
				confirm = getPositiveOneOrTwo(scanner, "Voulez-vous CONFIRMER LA VALIDATION:  ?(1:oui/ 2:non)");
				if (confirm == 1) {

					double totalprice = 0;
//					int lastOrderId = orderJob.getLastOrderId();
					int lastOrderItemId = orderLineJob.getLastOrderItemId();
					System.out.println(lastOrderItemId);
					//orderJob.insertOrderLineToOrder(new Order(lastOrderId + 1, new Date(), totalprice, userId));
					for (Entry<Integer, Article> entry : myBucket.entrySet()) {
						orderLineJob.insertOrderLine(new OrderLine(0, entry.getValue().getQuantity(), entry.getValue().getId(), lastOrderItemId+1));
						totalprice += entry.getValue().getPrice();
					}
					orderJob.insertOrderLineToOrder(new Order(0, new Date(), totalprice, userId, lastOrderItemId+1));
					//orderJob.updateOrder(new Order(lastOrderId + 1, null, totalprice, userId));
					myBucket.clear();
					System.out.println("Your bucket is cleared, thank you.");
				}
			}
		}
	}

	/**
	 * 
	 * @param bucketMap
	 */
	private static void showMyBucket(Map<Integer, Article> bucketMap) {
		/* Table header */
		System.out.println(
				"\n----------------------------------------------------------------------------------------------------");
		System.out.print(String.format("|%-5s|%-36s|%-18s|%-12s|%-10s|%-12s|", "ID", "DESCRIPTION", "BRAND",
				"UNITY PRICE", "QUANTITY", "TOTAL PRICE"));
		System.out.println(
				"\n----------------------------------------------------------------------------------------------------");

		/**
		 * Total price for each selected training and total price for the whole bucket
		 */
		Double productTotalPrice = 0.0;
		Double TotalPrice = 0.0;
		/* Start to fill the table body with the selected training */
		for (Entry<Integer, Article> entry : bucketMap.entrySet()) {
			productTotalPrice = entry.getValue().getPrice() * entry.getValue().getQuantity();
			System.out.println(String.format("|%-5s|%-36s|%-18s|%-12s|%-10s|%-12s|", entry.getKey(),
					entry.getValue().getDescription(), entry.getValue().getBrand(), entry.getValue().getPrice(),
					entry.getValue().getQuantity(), productTotalPrice + " €"));
			System.out.println(
					"----------------------------------------------------------------------------------------------------");
			TotalPrice += productTotalPrice;
		}

		/*
		 * The last table line: a small choice menu and the total bucket price in EURO
		 */
		System.out.println(String.format("\n|%-82s|%-15s|",
				"Totals:       -> -> ->         -> -> ->         -> -> ->         -> -> ->     ", TotalPrice + " €"));
		System.out.println(
				"----------------------------------------------------------------------------------------------------\n");

	}

	private static int getPositiveOneOrTwo(Scanner scanner2, String string) {
		String menuresponse = "";
		while (true) {
			System.out.println(string);
			try {
				menuresponse = scanner.next();
				if (Integer.parseInt(menuresponse) == 1 || Integer.parseInt(menuresponse) == 2)
					break;
				System.out.println("La réponse doit être numerique et positive entre 1 & 2");
			} catch (NumberFormatException e) {
				System.out.println("La réponse doit être numerique et positive entre 1 ou 2");
			}
		}
		return Integer.parseInt(menuresponse);

	}

	private static long getPositiveIntegerInput(Scanner scanner2, String string) {
		String menuresponse = "";
		while (true) {
			System.out.println(string);
			try {
				menuresponse = scanner.next();
				if (Integer.parseInt(menuresponse) >= 0)
					break;
				System.out.println("La réponse doit être numerique et positive >= 0");
			} catch (NumberFormatException e) {
				System.out.println("La réponse doit être numerique et positive >= 0");
			}
		}
		return Long.parseLong(menuresponse);
	}

}
