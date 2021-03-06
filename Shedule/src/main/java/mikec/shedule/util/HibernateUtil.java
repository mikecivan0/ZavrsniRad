/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikec.shedule.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

// singleton pattern
public class HibernateUtil {
    
    private static StandardServiceRegistry registry;
    private static Session session;
    // factory principle
    
    public static Session getSession() throws BaseException { 
        if (session == null) {
            
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
               SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                
                session=sessionFactory.openSession();
            } catch (Exception e) {
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
               throw new BaseException(e.getMessage());                
            }
        }
        return session;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    
}
