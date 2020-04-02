create table mahasiswa(
nim varchar(18) not null primary key,
nama varchar(50) not null,
jurusan varchar(25) not null,
prodi varchar(25) not null,
angkatan int not null,
tanggal_lahir date default null,
jenis_kelamin varchar(25) default null,
email varchar(25) default null,
hp varchar(13) default null,
unique(email)
);

create table perso(
serial varchar(16) not null primary key,
nopol varchar(12) not null,
tanggal datetime not null,
expired date not null,
active tinyint,
unique(nopol),
add foreign key(nim) references mahasiswa(nim)
);

create table kantong(
id varchar(40) not null primary key,
kode varchar(25) not null,
nama varchar(50) not null,
keterangan varchar(100) default null,
kapasitas int not null,
unique(kode)
);

create table rekap_live(
id varchar(40) not null primary key,
tanggal date,
kode varchar(50) not null,
terisi int default null,
sisa int default null,
catat time
);

create table rekap(
id varchar(40) not null primary key,
serial varchar(16) not null,
kode varchar(50) not null,
tanggal datetime,
in_out tinyint,
catat datetime
);