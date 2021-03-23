package com.example.demo;

import generated.World;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Justin
 */
public class Services {

    World world = new World();

    public World readWorldFromXml(String username) {
        JAXBContext jaxbContext;
        try {
            if (username != null) {
                jaxbContext = JAXBContext.newInstance(World.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                InputStream input = getClass().getClassLoader().getResourceAsStream(username + "world.xml");
                world = (World) jaxbUnmarshaller.unmarshal(input);
            } else {
                jaxbContext = JAXBContext.newInstance(World.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                InputStream input = getClass().getClassLoader().getResourceAsStream("world.xml");
                world = (World) jaxbUnmarshaller.unmarshal(input);
            }

        } catch (Exception ex) {
            System.out.println("Erreur lecture du fichier:" + ex.getMessage());
            ex.printStackTrace();
        }
        return world;
    }

    public void saveWordlToXml(World world, String username) {
        JAXBContext jaxbContext;
        try {
            if (username != null) {
                jaxbContext = JAXBContext.newInstance(World.class);
                Marshaller march = jaxbContext.createMarshaller();
                OutputStream output = new FileOutputStream(username + "world.xml");
                march.marshal(world, output);
            } else {
                jaxbContext = JAXBContext.newInstance(World.class);
                Marshaller march = jaxbContext.createMarshaller();
                OutputStream output = new FileOutputStream("world.xml");
                march.marshal(world, output);
            }
        } catch (Exception ex) {
            System.out.println("Erreur écriture du fichier:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public World getWorld(String username) {
        return readWorldFromXml(username);
    }

}
