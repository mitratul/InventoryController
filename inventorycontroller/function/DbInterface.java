/*
 * DbInterface.java
 *
 * Created on February 14, 2007, 19:22 HRS
 * Last Modified on April 21, 2007, 11:01 HRS
 */

package inventorycontroller.function;

public class DbInterface {
	
	/**
	 * Default constructor: only sets driver and driverClass to default value.
	 * DriverClass as JdbcOdbcDriver, Driver as MS Access Driver.
	 * Cannot be used before setting database source path, i.e. setDBQ() method.
	 */
	public DbInterface(){
		this.isDBQSet=false;
		this.driver="Microsoft Access Driver (*.mdb)";
		this.dbq="";
	}
	
	/**
	 * Constructor: sets the DBQ along with the connection with setting other default values.
	 */
	public DbInterface(java.lang.String dbq)
	throws java.lang.ClassNotFoundException, java.sql.SQLException{
		
		this.driver="Microsoft Access Driver (*.mdb)";
		this.dbq=new java.lang.String(dbq);
		this.isDBQSet=true;

		java.lang.Class.forName(this.driverClass);
		con=java.sql.DriverManager.getConnection(
			"jdbc:odbc:Driver={"+this.driver+"};DBQ="+this.dbq);

		this.isConSet=true;
	}
	
	/**
	 * Constructor: sets DBQ and Driver.
	 */
	public DbInterface(java.lang.String dbq, java.lang.String driver)
	throws java.lang.ClassNotFoundException, java.sql.SQLException{
		
		this.driver=new java.lang.String(driver);
		this.dbq=new java.lang.String(dbq);
		this.isDBQSet=true;
		
		java.lang.Class.forName(this.driverClass);
		con=java.sql.DriverManager.getConnection(
			"jdbc:odbc:Driver={"+this.driver+"};DBQ="+this.dbq);
		this.isConSet=true;
	}
	
	protected void finalize() {
		try{
			this.con.commit();
			this.con.close();			
		}
		catch(java.sql.SQLException ex){
			//create log.
		}
	}
	
	
	public void close() throws java.sql.SQLException{
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		
		this.con.commit();
		this.con.close();
		this.isConSet=false;
	}
	
		
	public void cmdDDL(java.lang.String cmd) throws java.sql.SQLException{
		if(!isDBQSet){
			throw new java.sql.SQLException("DBQ is not set.");
		}
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		
		java.sql.Statement stmt=con.createStatement(
			java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate(cmd);
		
		con.commit();
		stmt.close();
	}
	
	public void cmdDrop(java.lang.String table) throws java.sql.SQLException{
		this.cmdDDL("drop table "+table);
	}
	
	public void cmdDrop(java.lang.String table, java.lang.String constrain, 
	java.lang.String constrain_name) throws java.sql.SQLException{
		
	}
	
	
	public void cmdExecute(java.lang.String query) throws java.sql.SQLException{
		if(!isDBQSet){
			throw new java.sql.SQLException("DBQ is not set.");
		}
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		
		java.sql.Statement stmt=con.createStatement(
			java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_UPDATABLE);
		
		stmt.execute(query);
		stmt.close();
	}
	
	/**
	 * insertCmd(): inserts value.
	 */
	public void cmdInsert(java.lang.String table, java.lang.String values) 
	throws java.sql.SQLException{
		if(!isDBQSet){
			throw new java.sql.SQLException("DBQ is not set.");
		}
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		
		java.sql.Statement stmt=con.createStatement(
			java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("insert into "+table+" values("+values+")");
		
		con.commit();
		stmt.close();
	}
	
	public void cmdInsert(java.lang.String table, java.lang.String[] values)
	throws java.sql.SQLException{
		if(!isDBQSet){
			throw new java.sql.SQLException("DBQ is not set.");
		}
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		
		java.sql.Statement stmt=con.createStatement(
			java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_UPDATABLE);
		
		for (int i = 0; i<values.length; i++){
			stmt.executeUpdate("insert into "+table+" values("+values[i]+")");
		}
		con.commit();
		stmt.close();
	}
		
	/**
	 * selectCmd(): order by clause have to be inserted before group by clause, 
	 * i.e. 4th parameter: "groupByClause".
	 * having clause should be attached with group by clause. now GROUP BY clause may come 
	 * as 4th parameter or may be appended with WHERE clause, 3rd parameter.
	 * if the 2nd parameter, ATTRIBUTES, is blank string then it will return all the attributes.
	 */
	public java.lang.String[][] cmdSelect(java.lang.String table, java.lang.String attributes, 
	java.lang.String whereClause) throws java.sql.SQLException{
		if(!isDBQSet){
			throw new java.sql.SQLException("DBQ is not set.");
		}
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		
		if(attributes.length()==0){
			attributes="*";
		}
		if(whereClause.length()==0){
			whereClause="1=1";
		}
		java.sql.Statement stmt=con.createStatement(
			java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
		
		java.sql.ResultSet rs=stmt.executeQuery(
			"select "+attributes+" from "+table+" where "+whereClause);
					
		java.lang.String[][] res=toStringArray(rs);
		
		stmt.close();
		return res;
	}
	
		
	public java.lang.String[][] cmdSelect(java.lang.String table, java.lang.String attributes, 
	java.lang.String whereClause, java.lang.String groupByClause) throws java.sql.SQLException{
		if(whereClause.length()==0){
			whereClause="1=1";
		}
		return this.cmdSelect(table, attributes, whereClause+" "+groupByClause);
	}
	
	
	public void cmdUpdate(java.lang.String table, java.lang.String changes, 
	java.lang.String whereClause) throws java.sql.SQLException {
		if(!isDBQSet){
			throw new java.sql.SQLException("DBQ is not set.");
		}
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		if(whereClause.length()==0){
			whereClause="1=1";
		}
		java.sql.Statement stmt=con.createStatement(
			java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("update "+table+" set "+changes+" where "+whereClause);
		
		con.commit();
		stmt.close();
	}
	
	
	public java.lang.String[] getTables()throws java.sql.SQLException{
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		
		java.sql.ResultSet rst=con.getMetaData().getTables(null, null, "%", null);
		java.util.Vector<java.lang.String> v=new java.util.Vector<java.lang.String>();
		java.lang.String temp="";
		while(rst.next()){
			if(this.driver.compareTo("Microsoft Access Driver (*.mdb)")==0){
				temp=rst.getString(4);
				if(temp.compareToIgnoreCase("table")==0 || temp.compareToIgnoreCase("view")==0){
					v.add(rst.getString(3));
				}
			}
			else if(this.driver.compareTo("Microsoft Excel Driver (*.xls)")==0){
				temp=rst.getString(3);
				temp=temp.substring(0, temp.lastIndexOf("$"));
				if(temp.charAt(0)=='\''){
					temp=temp.substring(1);
				}
				v.add(temp);
				System.out.println ("trimmed: "+v.lastElement());
			}
		}
		java.lang.String[] tables=new java.lang.String[v.size()];
		for (int i = 0; i<tables.length; i++){
			tables[i]=v.get(i);
		}
		
		return tables;
	}
	
	
	public java.lang.String[] getColumns(java.lang.String table)throws java.sql.SQLException{
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		java.lang.String[] t=getTables();
		int i=0;
		for (; i<t.length; i++){
			if(t[i].compareTo(table)==0){
				break;
			}
		}
		if(i==t.length){
			throw new java.sql.SQLException("Table does not exist.");
		}
		if(this.driver.compareTo("Microsoft Excel Driver (*.xls)")==0){
			table=table+"$";
		}
		java.sql.Statement stmt=con.createStatement();
		java.sql.ResultSet rs=stmt.executeQuery("select * from ["+table+"] where 1=2");
		java.sql.ResultSetMetaData rsmd=rs.getMetaData();
		java.lang.String[] column=new java.lang.String[rsmd.getColumnCount()];
		for (int j = 0; j<column.length; j++){
			column[j]=new java.lang.String(rsmd.getColumnName(j+1));
		}
		return column;
	}
	
		
	public java.sql.Connection getCon() throws java.sql.SQLException{
		if(!isConSet){
			throw new java.sql.SQLException("Connection is not set.");
		}
		return this.con;
	}
	
	
	public java.lang.String getDBQ() throws java.sql.SQLException{
		if(!isDBQSet){
			throw new java.sql.SQLException("DBQ is not set.");
		}
		return this.dbq;
	}
	
	
	public java.lang.String getDriver(java.lang.String driver){
		return this.driver;
	}


	public java.lang.String getDriverClass(java.lang.String driverClass){
		return this.driverClass;
	}


	public void setCon()
	throws java.lang.ClassNotFoundException, java.sql.SQLException{
		
		if(!isDBQSet){
			throw new java.sql.SQLException("DBQ is not set.");
		}
		
		java.lang.Class.forName(this.driverClass);
		con=java.sql.DriverManager.getConnection(
			"jdbc:odbc:Driver={"+this.driver+"};DBQ="+this.dbq);
		this.isConSet=true;
	}
	
	
	public void setDBQ(java.lang.String dbq)
	throws java.lang.ClassNotFoundException, java.sql.SQLException{
		
		this.dbq=new java.lang.String(dbq);
		this.isDBQSet=true;
		
		java.lang.Class.forName(this.driverClass);
		con=java.sql.DriverManager.getConnection(
			"jdbc:odbc:Driver={"+this.driver+"};DBQ="+this.dbq);
		this.isConSet=true;
	}
	
	
	public void setDriver(java.lang.String driver){
		this.driver=new java.lang.String(driver);
	}

	
	/**
	 * toStringArray(): converts resultset to string array.
	 */
	private java.lang.String[][] toStringArray(java.sql.ResultSet rs)throws java.sql.SQLException{
		int r=0, c=0;
		
		c=rs.getMetaData().getColumnCount();
		rs.last();
		r=rs.getRow();
		
		java.lang.String[][] res=new java.lang.String[r][c];
		
		rs.beforeFirst();
		for(int i=0; rs.next()&& i<r; i++){
			res[i]=new java.lang.String[c];
			for(int j=0; j<c; j++){
				try{
					res[i][j]=new java.lang.String(rs.getString(j+1));
				}
				catch(java.lang.NullPointerException e){ //* if the value in db is NULL,
					res[i][j]=new java.lang.String("0"); //* set that to 0.
				}
			}
		}
		return res;
	}
	
	
	//*member variable declarations:
	private boolean isDBQSet;
	private boolean isConSet;
	private final java.lang.String driverClass="sun.jdbc.odbc.JdbcOdbcDriver";
	private java.lang.String driver;
	private java.lang.String dbq;
	private java.sql.Connection con;
		
}