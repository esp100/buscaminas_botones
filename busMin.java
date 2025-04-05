import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.ImageIO;


class busMin extends JFrame implements ActionListener{
	JLabel estat;
	JButton ini;
    JPanel p1;
    int victoria;

    mina[][] lugarMina;
    
    Container cont;
    GridBagConstraints c;       
    Image imgp;
    Image imgg;

	public busMin(){
            try{
                lugarMina= new mina[8][8];
                imgp = ImageIO.read(getClass().getResource("img/Mina.png"));
                imgg = ImageIO.read(getClass().getResource("/img/Bandera.png"));
                cont=getContentPane();
                c=new GridBagConstraints();

                setBounds(0,0,900,900);
                this.setResizable(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setTitle("Busca minas con botones");


                ini=new JButton("Reiniciar");
                ini.setPreferredSize(new Dimension(900, 90));
                cont.add("North",ini);
                ini.addActionListener(this);

                p1=new JPanel();
                p1.setLayout(new GridBagLayout());
                p1.setBounds(0, 0, 900, 720);
                p1.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));
                cont.add("Center",p1);

                estat=new JLabel(" ");
                estat.setPreferredSize(new Dimension(900, 90));
                cont.add("South",estat);

                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                        lugarMina[i][j]=new mina();
                        lugarMina[i][j].setPos(i*8 +j);
                        lugarMina[i][j].btn.setPreferredSize(new Dimension(78, 78));
                            c.weightx=1;
                            c.gridx=j;
                            c.gridy=i+1;
                        p1.add(lugarMina[i][j].btn,c);
                        lugarMina[i][j].btn.addActionListener(this); 
                        }
                    }
                    inicializar();
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println(e);
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()==ini) {
            new busMin().setVisible(true);
                this.dispose();
        }else{
            for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                    if (e.getSource()==lugarMina[i][j].getBtn() && lugarMina[i][j].isUsable()) {
                        if (lugarMina[i][j].isOcupado()) 
                            revelar(imgp,"Muerto");
                        else{
                            contar(i,j);
                            if(victoria==0)
                                revelar(imgg,"Felicidades");
                        } } }} }        
    }
    public void inicializar(){
        int posminas[]=aleatorio();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for(int en: posminas){  
                    if(lugarMina[i][j].getPos()==en){
                        lugarMina[i][j].setOcupado(true);
                    } 
                } } } 
        victoria=64-posminas.length;
        estat.setText("Casillas\nrestantes:\n"+String.valueOf(victoria));
        estat.setHorizontalAlignment(SwingConstants.CENTER);
        estat.setVerticalAlignment(SwingConstants.CENTER);
    }
    public void revelar(Image img, String txt){
        estat.setText(txt);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                lugarMina[i][j].getBtn().removeActionListener(this);
                if (lugarMina[i][j].isOcupado()) 
                    lugarMina[i][j].getBtn().setIcon(new ImageIcon(img));
                
        }}
    }
    
    public int[] aleatorio(){
        int[] aux=new int[15];
        boolean igual=true;
        Random rand = new Random();
        for(int e: aux)
            e=rand.nextInt(63);
                
        while(igual){
            igual=false;
            for(int e=0; e<aux.length; e++){
                for(int ec=0; ec<aux.length; ec++){
                    if(aux[e]==aux[ec] && ec!=e){
                        igual=true;
                        aux[ec]=rand.nextInt(63);
                    } } } }        
        return aux;
    }
    
    public void contar(int i, int j){
        lugarMina[i][j].setUsable(false);
        int conteo=0;
        if(i>=1){
            if( lugarMina[i-1][j].isOcupado() )
                conteo=conteo+1;
            if(j>=1)
                if( lugarMina[i-1][j-1].isOcupado() )
                    conteo=conteo+1;            
            if(j<=6)
                if( lugarMina[i-1][j+1].isOcupado() )
                    conteo=conteo+1;
        }
       
        
        if(i<=6){
            if( lugarMina[i+1][j].isOcupado() )
                conteo=conteo+1;
            if(j>=1)
                if( lugarMina[i+1][j-1].isOcupado() )
                    conteo=conteo+1;            
            if(j<=6)
                if( lugarMina[i+1][j+1].isOcupado() )
                    conteo=conteo+1;
        }
        
        if (j>=1)
            if( lugarMina[i][j-1].isOcupado() )
                conteo=conteo+1;    
                     
        if(j<=6)
            if( lugarMina[i][j+1].isOcupado() )
                conteo=conteo+1; 
        
        lugarMina[i][j].getBtn().setText(String.valueOf(conteo));
        victoria=victoria-1;
        estat.setText("Casillas restantes "+String.valueOf(victoria));
        
        checar(i,j);
        }
    
    public void checar(int i, int j){
        
        if(Integer.parseInt(lugarMina[i][j].getBtn().getText())==0){

                if(i>=1){
                    if(lugarMina[i-1][j].isUsable())
                        contar(i-1,j);
                    if(j>=1 && lugarMina[i-1][j-1].isUsable())
                            contar(i-1,j-1);          
                    if(j<=6 && lugarMina[i-1][j+1].isUsable())
                            contar(i-1,j+1);  
                }


                if(i<=6){
                    if(lugarMina[i+1][j].isUsable())
                        contar(i+1,j);   
                    if(j>=1 && lugarMina[i+1][j-1].isUsable())
                            contar(i+1,j-1);             
                    if(j<=6 && lugarMina[i+1][j+1].isUsable())
                            contar(i+1,j+1);   
                }

                if (j>=1 && lugarMina[i][j-1].isUsable())
                        contar(i,j-1);  

                if(j<=6 && lugarMina[i][j+1].isUsable())
                        contar(i,j+1);  
            }
    }

} 
