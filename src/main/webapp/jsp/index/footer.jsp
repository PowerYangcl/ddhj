</div>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- alert弹出层 -->

<!-- confirm弹出层 -->
<div id="confirm-modal" class="modal fade">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body" id="confirm-title"></div>
      <div class="modal-footer">
      	<a href="javascript:void(0)" class="btn btn-primary" id="confirm-agree" onclick="">确定</a>
        <a href="javascript:void(0)" class="btn btn-default" data-dismiss="modal" id="confirm-close">关闭</a>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- note弹出层 -->
<div id="note-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" style="display: none;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-body">
				<h4 class="modal-title" id="note-title"></h4>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<script type="text/javascript" src="assets/commons/js/nav.js"></script>
<script type="text/javascript" src="assets/commons/js/custom.js"></script>
</body>
</html>