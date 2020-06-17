public class Main {
    public static void main(String[] args) {
        if(Account.sv==null && !Account.isGiaoVu){
            HibernateUtil.getSessionFactory();
            Login login=new Login();
        }
        else {
            MainLayout layout=new MainLayout();
        }
    }
}
