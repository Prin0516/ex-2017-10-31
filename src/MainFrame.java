import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame {
    private LoginFrame loginFrame;
    private  Random rnd=new Random(System.currentTimeMillis());
    private JMenuBar jmb=new JMenuBar();
    private JMenu mF=new JMenu("File");
    private JMenu mS=new JMenu("Set");
    private JMenu mG=new JMenu("Game");
    private JMenu mA=new JMenu("About");
    private JMenuItem jmiexit=new JMenuItem("Exit");
    private JMenuItem jmiloto=new JMenuItem("樂透開獎");
    private JMenuItem jmifont=new JMenuItem("font set");
    private JButton jbtnexit=new JButton("Close");
    private JButton jbtnregen=new JButton("Generate");
    private JDesktopPane jdp=new JDesktopPane();
    private JInternalFrame jif=new JInternalFrame();
    private Container cp;
    private JPanel jpl=new JPanel(new GridLayout(1,6,3,3));
    private JPanel jpl1=new JPanel(new GridLayout(1,2,3,3));
    private String str[]={"PLAIN","BOLD","ITALIC","BOLD+ITALIC"};
    private JComboBox jcb=new JComboBox(str);
    private JPanel fontjpl=new JPanel(new GridLayout(2,3,5,5));
    private JLabel jlb[]=new JLabel[6];
    private JLabel familyjlb=new JLabel();
    private JLabel stylejlb=new JLabel();
    private JLabel sizejlb=new JLabel();
    private JTextField familyjtf=new JTextField("");
    private JTextField sizejtf=new JTextField("24");
    private int screenW=Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH=Toolkit.getDefaultToolkit().getScreenSize().height;
    private int width=500,height=500;

    private JMenuItem jmibook =new JMenuItem("Book");
    private JMenuItem jmicategory=new JMenuItem("Category");
    private JInternalFrame jiffile=new JInternalFrame();
    private JMenuBar jmbfile=new JMenuBar();
    private JMenu jmf=new JMenu("File");
    private JMenuItem jmiopen=new JMenuItem("Open");
    private JMenuItem jmiclose=new JMenuItem("Close");
    private JMenuItem jmiload=new JMenuItem("Load");
    private JFileChooser jfc=new JFileChooser();
    public MainFrame(LoginFrame log){
        loginFrame=log;
        init();
    }
    private void init(){
        this.setBounds(screenW/2-width/2,screenH/2-height/2,width,height);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                loginFrame.reset();
                loginFrame.setVisible(true);
            }
        });
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setJMenuBar(jmb);
        jmb.add(mF);
        jmb.add(mS);
        jmb.add(mG);
        jmb.add(mA);

        mF.add(jmibook);
        mF.add(jmicategory);
        mF.add(jmiexit);
        mG.add(jmiloto);
        mS.add(jmifont);

        fontjpl.add(familyjlb);
        fontjpl.add(stylejlb);
        fontjpl.add(sizejlb);
        fontjpl.add(familyjtf);
        fontjpl.add(jcb);
        fontjpl.add(sizejtf);
        this.setContentPane(jdp);
        cp=jif.getContentPane();
        cp.setLayout((new BorderLayout(5,5)));
        cp.add(jpl,BorderLayout.CENTER);
        cp.add(jpl1,BorderLayout.SOUTH);
        jpl1.add(jbtnexit);
        jpl1.add(jbtnregen);
        for(int i=0;i<6;i++){
            jlb[i]=new JLabel();
            jlb[i].setOpaque(true);
            jlb[i].setBackground(new Color(102, 189, 248));
            jpl.add(jlb[i]);
        }
        jmiexit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiloto.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              loginFrame.setVisible(true);
              dispose();
                loginFrame.reset();
            }
        });
        jmiloto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jif);
                jif.setBounds(10,10,300,100);
                jif.setVisible(true);
                jlabel();
            }
        });
        jmifont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result=JOptionPane.showConfirmDialog(MainFrame.this,fontjpl,"fontset",JOptionPane.OK_CANCEL_OPTION);
                int fontstyle=0;
                switch(jcb.getSelectedIndex()){
                    case 0:
                        fontstyle=Font.PLAIN;
                        break;
                    case 1:
                        fontstyle=Font.BOLD;
                        break;
                    case 2:
                        fontstyle=Font.ITALIC;
                        break;
                    case 3:
                        fontstyle=Font.BOLD+Font.ITALIC;
                        break;
                }
                if(result==JOptionPane.OK_OPTION){
                    UIManager.put("Menu.font",new Font(familyjtf.getText(),fontstyle,Integer.parseInt(sizejtf.getText())));
                    UIManager.put("MenuItem.font",new Font(familyjtf.getText(),fontstyle,Integer.parseInt(sizejtf.getText())));
                }


            }
        });

        jmibook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jiffile);
                jiffile.setBounds(10,10,200,200);
                jiffile.setVisible(true);
                jiffile.setJMenuBar(jmbfile);
                jmbfile.add(jmf);
                jmf.add(jmiopen);
                jmf.add(jmicategory);
                jmf.add(jmiclose);
            }
        });

        jbtnexit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jif.dispose();
            }

        });
        jbtnregen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlabel();
            }
        });
    }

    private void jlabel(){
        int data[]=new int[6];
        int i=0;
       while(i<6){
            int j=0;
            data[i]=rnd.nextInt(42)+1;
            boolean flag=true;
            while (j<i&&flag) {
                if(data[i]==data[j]){
                    flag=false;
                }
                j++;
            }
            if(flag){
                jlb[i].setText(Integer.toString(data[i]));
                i++;
            }
        }

    }


}