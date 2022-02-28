/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.fiscalias.services;

import com.prueba.fiscalias.database.SqlServer;
import com.prueba.fiscalias.models.FiscaliaModel;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dary Castillo
 */
public class FiscaliaService {

    SqlServer sqlServer = new SqlServer();

    public ArrayList getAll() throws SQLException, ClassNotFoundException {

        ArrayList<FiscaliaModel> Lista = new ArrayList<FiscaliaModel>();

        sqlServer.open();

        try ( Statement stmt = sqlServer.connection.createStatement();) {

            ResultSet rs = stmt.executeQuery("{call SP_SELECT_FISCALIA}");
            while (rs.next()) {

                FiscaliaModel fiscaliaModel = new FiscaliaModel();

                fiscaliaModel.setId(rs.getInt("id"));
                fiscaliaModel.setNombre(rs.getString("Nombre"));

                Lista.add(fiscaliaModel);
            }
        }

        sqlServer.close();

        return Lista;
    }

    public void create(FiscaliaModel fiscaliaModel) throws SQLException, ClassNotFoundException {

        sqlServer.open();

        try ( PreparedStatement pstmt = sqlServer.connection.prepareStatement("{call SP_INSERT_FISCALIA(?,?,?,?,?)}");) {

            pstmt.setString(1, fiscaliaModel.getNombre());
            pstmt.setInt(2, fiscaliaModel.getDepartamento_id());
            pstmt.setInt(3, fiscaliaModel.getMunicipio_id());
            pstmt.setString(4, fiscaliaModel.getDescripcion());
            pstmt.setString(5, fiscaliaModel.getTelefono());

            pstmt.execute();

            sqlServer.close();

        }

    }

    public void update(FiscaliaModel fiscaliaModel) throws SQLException, ClassNotFoundException {

        sqlServer.open();

        try ( PreparedStatement pstmt = sqlServer.connection.prepareStatement("{call SP_UPDATE_FISCALIA(?,?,?,?,?,?)}");) {

            pstmt.setInt(1, fiscaliaModel.getId());
            pstmt.setString(2, fiscaliaModel.getNombre());
            pstmt.setInt(3, fiscaliaModel.getDepartamento_id());
            pstmt.setInt(4, fiscaliaModel.getMunicipio_id());
            pstmt.setString(5, fiscaliaModel.getDescripcion());
            pstmt.setString(6, fiscaliaModel.getTelefono());

            pstmt.execute();

            sqlServer.close();

        }

    }

    public void delete(int fiscaliaID) throws SQLException, ClassNotFoundException {

        sqlServer.open();

        try ( CallableStatement cstmt = sqlServer.connection.prepareCall("{call SP_DELETE_FISCALIA (?)}");) {

            cstmt.setInt(1, fiscaliaID);
            cstmt.execute();

        }

        sqlServer.close();

    }

//public void createTelefono(SP_TELEFONO telefono) throws SQLException{
//              
//       con.AbrirConexion();
//     
//       try(PreparedStatement pstmt = con.conexion.prepareStatement("{call SP_INSERT_TELEFONO(?,?)}"); ) {  
//
//        pstmt.setInt(1,telefono.getFISCALIAID());  
//        pstmt.setString(2,telefono.getTELEFONONO());  
//
//        ResultSet rs = pstmt.executeQuery(); 
//
//
//      con.CerrarConexion();
//      
//   }
//   
// }
//   
//   
//    public void updateTelefono(SP_TELEFONO telefono) throws SQLException{
//              
//       con.AbrirConexion();
//     
//       try(PreparedStatement pstmt = con.conexion.prepareStatement("{call SP_UPDATE_TELEFONO(?,?)}"); ) {  
//
//        pstmt.setInt(1,telefono.getFISCALIAID());  
//        pstmt.setString(2,telefono.getTELEFONONO());  
//
//        ResultSet rs = pstmt.executeQuery(); 
//
//
//      con.CerrarConexion();
//      
//   }
//   
// }
//   
//    
// public ArrayList buscador(String arg) throws SQLException{
//
//       ArrayList<SP_FISCALIA> Lista=new ArrayList<SP_FISCALIA>();
//       
//       con.AbrirConexion();
//       
//        try(CallableStatement cstmt = con.conexion.prepareCall("{call SP_BUSCAR_FISCALIA(?)}"); ) {  
//            
//         cstmt.setString(1,arg);  
//
//         ResultSet rs= cstmt.executeQuery();
//  
//         while (rs.next()) { 
//             
//            SP_FISCALIA fis=new SP_FISCALIA();
//            
//            fis.setFISCALIAID(rs.getInt("FISCALIAID"));
//            fis.setFISCALIANOMBRE(rs.getString("FISCALIANOMBRE"));
//            fis.setDEPARTAMENTONOMBRE(rs.getString("DEPARTAMENTONOMBRE"));
//            fis.setMUNICIPIONOMBRE(rs.getString("MUNICIPIONOMBRE"));
//            fis.setFISCALIADIRCOMPLEMENTO(rs.getString("FISCALIADIRCOMPLEMENTO"));
//            fis.setTELEFONO(rs.getString("TELEFONONO"));
//            
//            Lista.add(fis);
//        }
//    } 
//        
//con.CerrarConexion();
//      
//return Lista;
//   
// }
//   
//
//
//
//  
}
