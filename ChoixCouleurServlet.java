/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "ChoixCouleurServlet", urlPatterns = {"/ChoixCouleurServlet"})
public class ChoixCouleurServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nbConnect="1";
        Cookie cookieConnect = new Cookie("nbConnect",nbConnect);
        response.addCookie(cookieConnect);

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/choixcouleur.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession();
        int choixCouleur=Integer.parseInt(request.getParameter("couleurChoisit"));
        
        String quelleCouleur=quelleCouleurAfficher(choixCouleur);
        
        modifCookie(request,response);
             
        session.setAttribute("quelleCouleur", quelleCouleur);
        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/choixcouleur.jsp");
        rd.forward(request, response);
    }

    private String quelleCouleurAfficher(int choixCouleur) {
        String quelleCouleur=null;
        
        switch(choixCouleur){
            case 1 :
                quelleCouleur="blue";
                break;
            case 2 :
                quelleCouleur="red";
                break;
            case 3 :
                quelleCouleur="green";
                break;
            case 4 :
                quelleCouleur="black";
                break;
                
                
        }
        return quelleCouleur;
    }

    private void modifCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie [] cookies=request.getCookies();
       
            for(Cookie cookie : cookies){
                if(cookie.getName().equalsIgnoreCase("nbConnect")){
                    
                   int valueCookie=1+Integer.parseInt(cookie.getValue());
                   cookie.setValue(String.valueOf(valueCookie));
                   request.setAttribute("nbConnect", cookie.getValue());
                   response.addCookie(cookie);
                }
            }
    }


}
