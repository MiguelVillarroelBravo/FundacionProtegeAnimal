function StatusMessage({ error, success }) {
  if (!error && !success) {
    return null;
  }

  return (
    <div className={error ? 'alert alert-danger' : 'alert alert-success'} role="alert">
      {error || success}
    </div>
  );
}

export default StatusMessage;
