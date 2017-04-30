#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "fancyslider.h"
#include "QLabel"
#include "QCheckBox"
#include <QSignalMapper>
#include <QDebug>
#include <QProcess>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    this->setWindowTitle("Projet SD - IBIS Ibrahim");


    this->productor = new FancySlider(this);
    productor->setGeometry(30,40,150,20);
    productor->setOrientation(Qt::Horizontal);
    QLabel *productor_label = new QLabel(this);
    productor_label->setText("Producteur d'or");
    productor_label->setGeometry(200,27,150,40);

    this->productargent = new FancySlider(this);
    productargent->setGeometry(30,70,150,20);
    productargent->setOrientation(Qt::Horizontal);
    QLabel *productargent_label = new QLabel(this);
    productargent_label->setText("Producteur d'argent");
    productargent_label->setGeometry(200,57,150,40);


    this->productbronze = new FancySlider(this);
    productbronze->setGeometry(30,100,150,20);
    productbronze->setOrientation(Qt::Horizontal);
    QLabel *productbronze_label = new QLabel(this);
    productbronze_label->setText("Producteur de bronze");
    productbronze_label->setGeometry(200,87,150,40);


    this->indi = new FancySlider(this);
    indi->setGeometry(30,130,150,20);
    indi->setOrientation(Qt::Horizontal);
    indi->setMinimum(0);
    indi->setValue(0);
    QLabel *indi_label = new QLabel(this);
    indi_label->setText("Client individuel");
    indi_label->setGeometry(200,117,150,40);



    this->coop = new FancySlider(this);
    coop->setGeometry(30,160,150,20);
    coop->setOrientation(Qt::Horizontal);
    coop->setMinimum(0);
    coop->setValue(0);
    QLabel *coop_label = new QLabel(this);
    coop_label->setText("Client cooperateur");
    coop_label->setGeometry(200,147,150,40);


    this->voleur = new FancySlider(this);
    voleur->setGeometry(30,190,150,20);
    voleur->setOrientation(Qt::Horizontal);
    voleur->setMinimum(0);
    voleur->setValue(0);
    QLabel *voleur_label = new QLabel(this);
    voleur_label->setText("Client voleur");
    voleur_label->setGeometry(200,177,150,40);


    this->prudent =new FancySlider(this);
    prudent->setGeometry(30,220,150,20);
    prudent->setMinimum(0);
    prudent->setValue(0);
    prudent->setOrientation(Qt::Horizontal);
    QLabel *prudent_label = new QLabel(this);
    prudent_label->setText("Client prudent");
    prudent_label->setGeometry(200,207,150,40);


    this->coopprudent= new FancySlider(this);
    coopprudent->setGeometry(30,250,150,20);
    coopprudent->setMinimum(0);
    coopprudent->setValue(0);
    coopprudent->setOrientation(Qt::Horizontal);
    QLabel *coopprudent_label = new QLabel(this);
    coopprudent_label->setText("Client coop et malin");
    coopprudent_label->setGeometry(200,237,160,40);


    this->ordo = new QCheckBox("Ordonnancement inactif",this);
    ordo->setGeometry(400,27,250,40);
    this->humain = new QCheckBox("Humain vs ordinateur inactif",this);
    humain->setGeometry(400,57,250,40);
    humain->setCheckable(false);
    humain->setStyleSheet("color : grey;");
    this->epuisable = new QCheckBox("Ressource illimité",this);
    epuisable->setGeometry(400,87,250,40);
    this->observation = new QCheckBox("Observation inactif",this);
    observation->setGeometry(400,117,250,40);
    this->vol = new QCheckBox("Vol inactif",this);
    vol->setGeometry(400,147,250,40);
    this->fin = new QCheckBox("Fin de partie : Tous les joueurs finissent",this);
    fin->setGeometry(400,177,290,40);

    QSignalMapper* signalMapper = new QSignalMapper (this) ;








    signalMapper->setMapping(ordo,ordo) ;
    QObject::connect(ordo, SIGNAL(stateChanged(int)), signalMapper, SLOT(map())) ;
    signalMapper->setMapping(humain,humain) ;
    QObject::connect(humain, SIGNAL(stateChanged(int)), signalMapper, SLOT(map())) ;
     signalMapper->setMapping(epuisable,epuisable) ;
     QObject::connect(epuisable, SIGNAL(stateChanged(int)), signalMapper, SLOT(map())) ;
      signalMapper->setMapping(observation,observation) ;
      QObject::connect(observation, SIGNAL(stateChanged(int)), signalMapper, SLOT(map())) ;
       signalMapper->setMapping(vol,vol) ;
       QObject::connect(vol, SIGNAL(stateChanged(int)), signalMapper, SLOT(map())) ;
       signalMapper->setMapping(fin,fin) ;
        QObject::connect(fin, SIGNAL(stateChanged(int)), signalMapper, SLOT(map())) ;
    QObject::connect(signalMapper, SIGNAL(mapped(QWidget*)), this, SLOT(changement(QWidget*)));

}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::changement(QWidget *ordo)
{
    if(((QCheckBox*)ordo)->text() == "Ordonnancement actif" )
            ((QCheckBox*)ordo)->setText("Ordonnancement inactif");


    else if(((QCheckBox*)ordo)->text() == "Ordonnancement inactif" )
            ((QCheckBox*)ordo)->setText("Ordonnancement actif");

    else if(((QCheckBox*)ordo)->text() == "Humain vs ordinateur inactif" )
            ((QCheckBox*)ordo)->setText("Humain vs ordinateur actif");

    else if(((QCheckBox*)ordo)->text() == "Humain vs ordinateur actif" )
            ((QCheckBox*)ordo)->setText("Humain vs ordinateur inactif");

    else if(((QCheckBox*)ordo)->text() == "Ressource épuisable" )
            ((QCheckBox*)ordo)->setText("Ressource illimité");

    else if(((QCheckBox*)ordo)->text() == "Ressource illimité" )
            ((QCheckBox*)ordo)->setText("Ressource épuisable");

    else if(((QCheckBox*)ordo)->text() == "Observation inactif" )
            ((QCheckBox*)ordo)->setText("Observation actif");

    else if(((QCheckBox*)ordo)->text() == "Observation actif" )
            ((QCheckBox*)ordo)->setText("Observation inactif");

    else if(((QCheckBox*)ordo)->text() == "Vol inactif" )
            ((QCheckBox*)ordo)->setText("Vol actif");

    else if(((QCheckBox*)ordo)->text() == "Vol actif" )
            ((QCheckBox*)ordo)->setText("Vol inactif");

    else if(((QCheckBox*)ordo)->text() == "Fin de partie : Premier joueur qui fini" )
            ((QCheckBox*)ordo)->setText("Fin de partie : Tous les joueurs finissent");

    else if(((QCheckBox*)ordo)->text() == "Fin de partie : Tous les joueurs finissent" )
            ((QCheckBox*)ordo)->setText("Fin de partie : Premier joueur qui fini");


}


void MainWindow::on_pushButton_clicked()
{
    int ordoo,humainn,epuii,obss,voll,finn;
    if ( !ordo->isChecked())
        ordoo = 0;
    else
        ordoo = 1;

    if ( !humain->isChecked())
        humainn = 0;
    else
        humainn = 1;

    if ( !epuisable->isChecked())
        epuii = 0;
    else
        epuii = 1;

    if ( !observation->isChecked())
        obss = 0;
    else
        obss = 1;

    if ( !vol->isChecked())
        voll = 0;
    else
        voll = 1;

    if ( !fin->isChecked())
        finn = 0;
    else
        finn = 1;

    QProcess *proc = new QProcess();
    proc->start("sh", QStringList() << "-c" << "./detruit");
    proc->waitForStarted();



    std::string arguments= "./construit " + std::to_string(indi->value()) + " " + std::to_string(coop->value()) + " " + std::to_string(voleur->value()) + " " + std::to_string(prudent->value()) + " " + std::to_string(coopprudent->value()) + " "+ std::to_string(productor->value()) + " " + std::to_string(productargent->value()) + " " + std::to_string(productbronze->value()) + " " + std::to_string(ordoo) + " " + std::to_string(humainn) + " " + std::to_string(epuii) + " "  + std::to_string(obss) + " " + std::to_string(voll) + " " + std::to_string(finn)   ;
    const char *cstr = arguments.c_str();
    QProcess *proc2 = new QProcess();
    proc2->start("sh", QStringList() << "-c" << cstr  );
    proc2->waitForStarted();


}
