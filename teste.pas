program Somatorio
var
  Ini, Fim, Conta, Soma : integer;
begin

  Ini  := 0;
  Fim  := 0;
  Soma := 0;
  read( Ini, Fim );
  for Conta := Ini to Fim do begin
    Soma := Soma + Conta;
  end;
  write( Soma );
end.