import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JLabel jlbID=new JLabel("ID:");
    private JLabel jlbPW=new JLabel("Password:");
    private JTextField jtfID=new JTextField();
    private JPasswordField jpf=new JPasswordField();
    private JButton jbtnlogin=new JButton("Login");
    private JButton jbtneixt=new JButton("Exit");
    private Container cp;
    private Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
    private int width=300,height=150,minwidth,minheight;
    public LoginFrame(){
        init();
    }
    public void init(){
        minwidth=dim.width;
        minheight=dim.height;
        this.setBounds(minwidth/2-width/2,minheight/2-height/2,width,height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp=this.getContentPane();
        cp.setLayout(new GridLayout(3,2, 3,5));
        jlbID.setHorizontalAlignment(SwingConstants.RIGHT);
        jlbPW.setHorizontalAlignment(SwingConstants.RIGHT);
        cp.add(jlbID);
        cp.add(jtfID);
        cp.add(jlbPW);
        cp.add(jpf);
        cp.add(jbtnlogin);
        cp.add(jbtneixt);
        jbtneixt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbtnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtfID.getText().equals("h304")&&new String(jpf.getPassword()).equals("23323456")){
                    MainFrame mf= new MainFrame(LoginFrame.this);
                    mf.setVisible(true);
                    LoginFrame.this.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(LoginFrame.this,"Error");
                }
            }
        });
    }

    public void reset() {
        jtfID.setText("");
        jpf.setText("");
    }
}
