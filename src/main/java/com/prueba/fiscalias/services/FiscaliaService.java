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

        try (Statement stmt = sqlServer.connection.createStatement();) {

            ResultSet rs = stmt.executeQuery("{call SP_SELECT_FISCALIA}");
            while (rs.next()) {

                FiscaliaModel fiscaliaModel = new FiscaliaModel();

                fiscaliaModel.setId(rs.getInt("FISCALIAID"));
                fiscaliaModel.setNombre(rs.getString("FISCALIANOMBRE"));

                Lista.add(fiscaliaModel);
            }
        }

        sqlServer.close();

        return Lista;
    }

//   
//    public ArrayList getDepartamentos() throws SQLException{
//       
//       ArrayList<DEPARTAMENTO> Lista=new ArrayList<DEPARTAMENTO>();
//       
//       con.AbrirConexion();
//     
//       try(Statement stmt = con.conexion. createStatement();) {  
//
//        ResultSet rs = stmt.executeQuery("{call SP_SELECT_DEPARTAMENTOS}");  
//        while (rs.next()) { 
//            
//           DEPARTAMENTO dep=new DEPARTAMENTO();
//            
//           dep.setDEPARTAMENTOID(rs.getInt("DEPARTAMENTOID"));
//           dep.setDEPARTAMENTONOMBRE(rs.getString("DEPARTAMENTONOMBRE"));
//            
//            Lista.add(dep);
//        }
//      
//    }  
//       
//      con.CerrarConexion();
//      
//      return Lista;
//   }
//   
//   
//   
//    public ArrayList getMunicipios(int depto) throws SQLException{
//       
//       ArrayList<MUNICIPIO> Lista=new ArrayList<MUNICIPIO>();
//       
//       con.AbrirConexion();
//     
//       try(CallableStatement cstmt = con.conexion.prepareCall("{call SP_SELECT_MUNICIPIOS(?)}"); ) {  
//            
//         cstmt.setInt(1,depto);  
//
//         ResultSet rs= cstmt.executeQuery();
//  
//         while (rs.next()) { 
//             
//            MUNICIPIO mun=new MUNICIPIO();
//            
//            mun.setDEPARTAMENTOID(rs.getInt("DEPARTAMENTOID"));
//            mun.setMUNICIPIOID(rs.getInt("MUNICIPIOID"));
//            mun.setMUNICIPIONOMBRE(rs.getString("MUNICIPIONOMBRE"));
//          
//            Lista.add(mun);
//        }
//    } 
//       
//       
//      con.CerrarConexion();
//      
//      return Lista;
//   }
//   
//   
//
//   
//public void createFiscalia(FISCALIA fiscalia) throws SQLException{
//              
//       con.AbrirConexion();
//     
//       try(PreparedStatement pstmt = con.conexion.prepareStatement("{call SP_INSERT_FISCALIA(?,?,?,?,?)}"); ) {  
//
//        pstmt.setString(1, fiscalia.getFISCALIANOMBRE());  
//        pstmt.setInt(2, fiscalia.getDEPARTAMENTOID());  
//        pstmt.setInt(3, fiscalia.getMUNICIPIOID());  
//        pstmt.setString(4,fiscalia.getFISCALIADIRCOMPLEMENTO()); 
//        pstmt.setString(5,fiscalia.getTELEFONO()); 
//        
//        ResultSet rs = pstmt.executeQuery(); 
//
//      con.CerrarConexion();
//      
//   }
//   
// }
//    
//    
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
//    public void updateFiscalia(FISCALIA fiscalia) throws SQLException{
//              
//       con.AbrirConexion();
//     
//       try(PreparedStatement pstmt = con.conexion.prepareStatement("{call SP_UPDATE_FISCALIA(?,?,?,?,?)}"); ) {  
//
//        pstmt.setInt(1,fiscalia.getFISCALIAID());
//        pstmt.setString(2, fiscalia.getFISCALIANOMBRE());  
//        pstmt.setInt(3, fiscalia.getDEPARTAMENTOID());  
//        pstmt.setInt(4, fiscalia.getMUNICIPIOID());  
//        pstmt.setString(5,fiscalia.getFISCALIADIRCOMPLEMENTO()); 
//        
//        ResultSet rs = pstmt.executeQuery(); 
//
//      con.CerrarConexion();
//      
//      //se actualiza el telefono
//      SP_TELEFONO tl=new SP_TELEFONO();
//      tl.setFISCALIAID(fiscalia.getFISCALIAID());
//      tl.setTELEFONONO(fiscalia.getTELEFONO());
//      this.updateTelefono(tl);
//   }
//   
// }
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
//    public void deleteFiscalia(int fiscaliaID) throws SQLException{
//              
//       con.AbrirConexion();
//     
//       try(CallableStatement cstmt = con.conexion.prepareCall("{call SP_DELETE_FISCALIA (?)}"); ) {  
//           
//        cstmt.setInt(1, fiscaliaID);  
//        cstmt.execute();  
// 
//        }  
//       
//      con.CerrarConexion();
//      
//   }
}
