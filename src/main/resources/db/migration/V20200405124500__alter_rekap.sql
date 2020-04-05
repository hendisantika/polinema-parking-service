alter table rekap
change tanggal tanggal_masuk datetime;

alter table rekap
change in_out masuk tinyint;

alter table rekap
add tanggal_keluar datetime after tanggal_masuk,
add keluar tinyint(1) after masuk;