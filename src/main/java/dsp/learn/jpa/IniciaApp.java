package dsp.learn.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class IniciaApp {

	public static Cliente cliente;

	public static void main(String[] args) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cursojpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			cliente = new Cliente();
//			cliente.setId(11);
			cliente.setFirstName("Claudia");
			cliente.setLastName("Fontenelli");
			inserirCliente(cliente, em);
			
			cliente = em.find(Cliente.class, 24);
			removerCliente(cliente, em);

//			cliente = new Cliente();
//			cliente.setId(2);
//			cliente.setFirstName("Amanda");
//			cliente.setLastName("Fontanna");
//			salvarCliente(cliente, em);

//			removerCliente(-1, em);
//			removerCliente(15, em);
//			removerCliente(16, em);
//			removerCliente(18, em);

			
			// Se fizer o flush() após a exclusão do objeto, funciona...
			// Se não fizer o flush(), o EntityManager dá pau...
			cliente = em.find(Cliente.class, 2);
			Cliente cli2 = em.find(Cliente.class, 3);
			removerCliente(cliente, em); 
			em.flush();
			removerCliente(cli2, em); 
			em.flush(); 
			cliente.setId(3);
			cli2.setId(2);
			inserirCliente(cliente, em); 
			em.flush();
			inserirCliente(cli2, em); 
			em.flush();
			
//			cliente.setFirstName("Breno");
//			cliente.setLastName("Bertanni");
//			alterarCliente(cliente, em);
//
//			cliente = em.find(Cliente.class, -1);
//			cliente.setId(1);
////			cliente.setFirstName("Breno");
////			cliente.setLastName("Bertanni");
//			alterarCliente(cliente, em);

			transaction.commit();

		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				System.out.println("Efetuando RollBack...");
				transaction.rollback();
			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>> IMPRIMINDO A MENSAGEM DE EXCEÇÃO <<<<<<<<<<");
			e.printStackTrace();
		} finally {
//		    if (em != null && em.isOpen()) {                                        
		        em.close();  
		        em.close();
		        emf.close();
		        emf.close();
//		    }          
		}
	}

	public static void inserirCliente(Cliente cliente, EntityManager em) throws Exception {

		try {
			System.out.println("\nInserindo cliente...");
			System.out.println("cliente id = " + cliente.getId());
			System.out.println("cliente firstname = " + cliente.getFirstName());
			System.out.println("cliente lastname = " + cliente.getLastName());
			em.persist(cliente);
			System.out.println("Cliente inserido!\n");

		} catch (Exception e) {
			
		}

	}

	public static void removerCliente(Cliente cliente, EntityManager em) throws Exception {

		try {
			System.out.println("\nRemovendo cliente...");
			System.out.println("cliente id = " + cliente.getId());
			System.out.println("cliente firstname = " + cliente.getFirstName());
			System.out.println("cliente lastname = " + cliente.getLastName());
			em.remove(cliente);
			System.out.println("Cliente removido!\n");

		} catch (Exception e) {

		}

	}

	public static void alterarCliente(Cliente cliente, EntityManager em) throws Exception {

		try {

			System.out.println("\nAlterando cliente...");
			System.out.println("cliente id = " + cliente.getId());
			System.out.println("cliente firstname = " + cliente.getFirstName());
			System.out.println("cliente lastname = " + cliente.getLastName());
			em.merge(cliente);
			System.out.println("Cliente alterado!\n");

		} catch (Exception e) {

		}

	}

}
