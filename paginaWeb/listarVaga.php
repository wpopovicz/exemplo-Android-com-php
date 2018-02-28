<html>
    <head><meta http-equiv="Content-Type" content="text/html; charset=gb18030">
        <?php include 'header.php'; ?>
    </head>
    <body>
        <div id="meuId">
        <?php include 'menu.php'; ?>
        <h1 class="main-title">Vagas Dispon&iacute;veis</h1>
        <div class="main-border"></div>
        <?php

	$json_file = file_get_contents("http://www.weaplicativos.com/web/v4/listaVaga.php");
	
	$json_str = json_decode($json_file);
	
	$itens = $json_str->vaga;
	?>
	<div class="container">
		<div class="row">
		<?php foreach ( $itens as $e ){ ?>	
			<div class="col-md-4">
			<a  data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo" class="ng-scope">
	                        <div class="vaga animacao text-center">
	                            <span class="ng-binding"><?php echo $e->titulo; ?></span>
	                        </div>
                    	</a>
			</div>
		<?php } ?>
		</div>
	</div>
	<div class="contact trabalhe" id="contact" style="background: #1d71b8; padding: 50px 0px;">
	        <div class="container">
	            <h1 class="main-title" style="color: #fff;">Cadastrar Curr&iacute;culo</h1>
	            <div class="main-border"></div>
	            
	            <form id="contact-form" action="/web/v4/inserirCandidato.php" method="post" class="ng-pristine ng-invalid ng-invalid-required ng-valid-email">

	                <div class="row">
	                    <div class="col-md-6">
	                        <div class="form-group">
	                            <input name="nome" id="nome" ng-model="curriculo.nome" class="form-control ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required" type="text" placeholder="Nome*" required="">
	                        </div>
	                        <div class="form-group">
	                            <input name="endereco" id="endereco" ng-model="curriculo.idade" class="form-control ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required" type="text" placeholder="Endere&ccedil;o*" required="" >
	                        </div>
	                        <div class="row">
	                            <div class="form-group col-md-6">
	                                <input name="telefone" id="telefone" ng-model="curriculo.celular" class="form-control ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required" type="text" placeholder="Telefone*" required="" maxlength="15">
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <input class="form-control ng-pristine ng-untouched ng-empty ng-valid-email ng-invalid ng-invalid-required" name="email" ng-model="curriculo.email" id="mail" type="email" placeholder="E-mail*" required="">
	                        </div>
	
	                    </div>
	                    <div class="col-md-6">
	                        <div class="form-group">
	                            <label style="color: #fff;">Forma&ccedil;&atilde;o</label>
	                            <select class="form-control ng-pristine ng-untouched ng-valid ng-empty" ng-model="curriculo.formacao" id="formacao_academica" name="formacao_academica"><option value="? undefined:undefined ?" selected="selected"></option>
	                                <option value="Ensino Médio">Ensino M&eacute;dio</option>
	                                <option value="Graduado">Graduado</option>
	                                <option value="Pós">P&oacute;s Graduado</option>
	                                <option value="Mestrado">Mestrado</option>
	                            </select>
	                        </div>
	                        
	                        <div class="form-group">
	                            <label style="color: #fff;">vaga</label>
	                            <select class="form-control ng-pristine ng-untouched ng-valid ng-empty" ng-model="curriculo.formacao" id="id_vaga" name="id_vaga"><option value="? undefined:undefined ?" selected="selected"></option>
	                            <?php foreach ( $itens as $v ){ ?>	
	                                <option value="<?php echo $v->id; ?>"><?php echo $v->titulo; ?></option>
	                            <?php } ?>
	                            </select>
	                        </div>
	                        <div class="form-group">
	                            <input type="text" id="objetivo" name="objetivo" placeholder="Objetivo">
	                        </div>
	                        <div class="form-group">
	                            <input type="text" id="informacao_adicional" name="informacao_adicional" placeholder="informa&ccedil;&atilde;o adicional">
	                        </div>
	                    </div>
	                    <div class="form-group col-md-12">
	                        <textarea class="form-control ng-pristine ng-untouched ng-valid ng-empty" ng-model="curriculo.resumo" name="experiencia_profissional" id="experiencia_profissional" placeholder="Resumo Profissional"></textarea>
	                    </div>
	                </div>
	                <input type="submit" value="Enviar" ng-click="Enviar()">
	            </form>
	            <!-- end contactfort -->
        </div>
    
	</div>
	<footer>
		<?php include 'footer.php' ?>
	</footer>
        </div>
        
        <?php include 'footerJs.php' ?>
    </body>
    
</html>
